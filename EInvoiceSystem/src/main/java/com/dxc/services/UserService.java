package com.dxc.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.dxc.models.User;

public interface UserService {
	
	HttpStatus register(@RequestBody User req);
	
	User findById(int id);
	
	User findByEmail(String email);
	
	HttpStatus changeUserState(User req);
	
	ResponseEntity<List<User>> getMemberList();
}
