package com.dxc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<Void> register(@RequestBody User req) {
	
		return new ResponseEntity<Void>(userService.register(req));
	}
	
	@RequestMapping(value = "/user/state", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> changeState(@RequestBody User req) {
	
		return new ResponseEntity<Void>(userService.changeUserState(req));
	}
}
