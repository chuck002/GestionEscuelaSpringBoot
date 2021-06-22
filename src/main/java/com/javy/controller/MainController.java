package com.javy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javy.entity.Users;
import com.javy.services.UserServiceImplementation;

@Controller
public class MainController {
	@Autowired
	private UserServiceImplementation user;
	
	private Users uTmp;

	@GetMapping({"/","/home","/index"})
	public String index(Model modelo) {
		
		Object userObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (userObject instanceof UserDetails) {
		  userDetails = (UserDetails) userObject;
		}
		List<Users> users = user.getUsers();
		for(Users u : users) {
			if(u.getUsername_user().equals(userDetails.getUsername())) {
				uTmp = user.getUser(u.getId());
			}
		}
		if(uTmp.getUsername_user().equals("admin")) {
			modelo.addAttribute("users", users);
		}
		modelo.addAttribute("user", uTmp);
		return "index";
	}
	
	@RequestMapping("/403")
	public String error403() {
		
		return "errors/403";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/other_logout")
	public String logout() {
		
		return "logout";
	}
}
