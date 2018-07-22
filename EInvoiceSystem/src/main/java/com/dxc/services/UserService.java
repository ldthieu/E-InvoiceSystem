package com.dxc.services;

import com.dxc.models.User;

public interface UserService {
	
	void saveUser(User user);
	
	User findById(int id);
	
	User findByEmail(String email);
}
