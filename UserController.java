package com.usermgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.usermgm.model.User;
import com.usermgm.service.UserService;

@Controller
public class UserController 
{
	
	@Autowired
	private UserService userService;
		
	
		@GetMapping("/")
		public String homePage()
		{	
			return "index";
		}
	
		
		@GetMapping("/register")
		public String showRegisterForm(Model model)
		{
			model.addAttribute("user",new User());
			
			return "register";
		}
		
		
		@PostMapping("/register")
		public String registerUser(@ModelAttribute User user)
		{
			userService.saveUser(user);
			return "redirect:/login";
		}
		
		
		@GetMapping("/login")
		public String login()
		{
			return "login";
		}

}