package com.lcwak.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcwak.bean.User;
import com.lcwak.repository.UserRepo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
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
	
	@GetMapping("/profile")
	public String profile()
	{
		return "admin/admin_profile";
	}
}
