package com.dxc.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.dxc.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		if (username != "anonymousUser") {
			boolean isActive = userRepository.findByEmail(username).isActive();
			System.out.println(isActive);
			if (isActive) {
				return "index";
			}
		}
		return "redirect:/login?unauthorized";
	}
	
	@GetMapping("/admin") 
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping("/login") 
	public String getLogin() {
		return "login";
	} 
	
	@GetMapping("/register") 
	public String register() {
		return "register";
	}
	
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	
	@GetMapping("/invoice/create")
	public String createInvoice() {
		return "invoice-create";
	}
	
	@GetMapping("/invoice/update/{id}")
	public String updateInvoice(@PathVariable String id) {
		return "invoice-update";
	}
	
	@GetMapping("/invoice")
	public String invoice() {
		return "invoice";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@GetMapping("/chart")
	public String chart() {
		return "chart";
	}
	
	@GetMapping("/user/state")
	public String userState() {
		return "user-state";
	}
}
