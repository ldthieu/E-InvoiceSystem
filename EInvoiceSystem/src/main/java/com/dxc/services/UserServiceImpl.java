package com.dxc.services;

import com.dxc.models.User;
import com.dxc.repository.UserRepository;

public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

}