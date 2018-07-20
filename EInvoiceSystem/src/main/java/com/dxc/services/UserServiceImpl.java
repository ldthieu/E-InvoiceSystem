package com.dxc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.models.User;
import com.dxc.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

}