package com.lcwak.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcwak.bean.User;
import com.lcwak.repository.UserRepo;
import com.lcwak.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@ModelAttribute
	public void commonUser(Principal p,Model m)
	{
		if(p!=null)
		{
			String email=p.getName();
			User user=userRepo.findByEmail(email);
			m.addAttribute("user",user);
		}
		
	}
	
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/signin")
	public String signIn()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	/*
	@GetMapping("/user/profile")
	public String profile(Principal p,Model m)
	{
		String email=p.getName();
		User user=userRepo.findByEmail(email);
		m.addAttribute("user",user);
		return "profile";
	}
	
	@GetMapping("/user/home")
	public String home()
	{
		return "home";
	}
	*/
	@PostMapping("/saveuser")
	public String saveUser(@ModelAttribute User user,HttpSession session)
	{
		
		/*boolean f=userService.checkEmail(user.getEmail());
		
		if(f)
		{
			System.out.println("Email id already exist");
			//session.setAttribute("msg", "Email ID aleady exist");
		}else}*/
			String password=bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(password);
			user.setRole("ROLE_USER");
			
			User  u=userService.saveUser(user);
			
			if(u!=null)
			{
			//System.out.println("SAVE SUCCESS");	
				session.setAttribute("msg", "Register Sucessfully");
			}else {
				//System.out.println("ERROR");
				session.setAttribute("msg", "Something went wrong on server");
			}
			
		
		return "redirect:/register";
	}
	@GetMapping("/loadforgotpassword")
	public String loadForgotPassword()
	{
		return "forgot_password";
	}
	@GetMapping("/loadresetpassword/{id}")
	public String loadResetPassword(@PathVariable int id,Model m)
	{
		//System.out.println(id);
		m.addAttribute("id",id);
		return "reset_password";
	}
	@PostMapping("/forgotpassword")
	public String forgotPassword(@RequestParam String email,@RequestParam String mobilenum,HttpSession session)
	{
	User user=userRepo.findByEmailAndMobileno(email, mobilenum);
		if(user!=null)
		{
			return "redirect:/loadresetpassword/"+user.getId();
			
		}else {
			session.setAttribute("msg", "Invalid email and mobile number");
			return "forgot_password";
		}
		
		
	}
	@PostMapping("/changepassword")
	public String resetPassword(@RequestParam String pwd,@RequestParam int id,HttpSession session)
	{
		User user=userRepo.findById(id).get();
		
		String encryptPwd=bCryptPasswordEncoder.encode(pwd);
		
		user.setPassword(encryptPwd);
		
		User updateUser=userRepo.save(user);
		
		if(updateUser!=null)
		{
			session.setAttribute("msg", "Password Change Successfully");
		}
		
		return "redirect:/loadforgotpassword";
	}
}
