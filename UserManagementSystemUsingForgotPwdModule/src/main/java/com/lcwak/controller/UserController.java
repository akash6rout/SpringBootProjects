package com.lcwak.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcwak.bean.User;
import com.lcwak.repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepo userRepo;

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
	@GetMapping("/resume")
	public String resume()
	{
		return "user/resume";
	}
	
	@GetMapping("/profile")
	public String profile()
	{
		return "user/user_profile";
	}
	@GetMapping("/changepassword")
	public String loadChangePassword()
	{
		return "user/change_password";
	}
	
	@PostMapping("/updatepassword")
	public String changePassword(Principal p,@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass,HttpSession session)
	{
		String email=p.getName();
		User loginuser=userRepo.findByEmail(email);
		boolean f=bCryptPasswordEncoder.matches(oldPass, loginuser.getPassword());
		if(f)
		{
			loginuser.setPassword(bCryptPasswordEncoder.encode(newPass));
			User updatePass=userRepo.save(loginuser);
			if(updatePass!=null)
			{
				session.setAttribute("msg", "Password Change Success");
			}else
			{
				session.setAttribute("msg", "Old Password incorect");

			}
			//System.out.println("Old Password is correct");
		}
		else {
			//System.out.println("wrong old password");
		}
		return "redirect:/user/change_password";
	}
}
