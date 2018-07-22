package com.dxc.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dxc.models.Role;
import com.dxc.models.User;
import com.dxc.services.RoleService;
import com.dxc.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> register(@RequestBody User req, UriComponentsBuilder ucBuilder) {
		
		//	check req
		if (req.getEmail() == null || req.getFullname() == null || req.getPassword() == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if(userService.findByEmail(req.getEmail()) == null) {
			User user = new User();
			user.setEmail(req.getEmail());
			user.setActive(true);
			user.setPassword(passwordEncoder.encode(req.getPassword()));
			user.setFullname(req.getFullname());
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleService.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userService.saveUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}	
	}
}
