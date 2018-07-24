package com.dxc.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import com.dxc.models.User;

public interface UserService {
	
	HttpStatus register(@RequestBody User req);
	
	User findById(int id);
	
	User findByEmail(String email);
}
