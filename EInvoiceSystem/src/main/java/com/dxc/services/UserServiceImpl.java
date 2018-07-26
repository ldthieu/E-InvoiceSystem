package com.dxc.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.models.Role;
import com.dxc.models.User;
import com.dxc.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleService roleService;

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public HttpStatus register(User req) {
		// TODO Auto-generated method stub
		if (req.getEmail() == null || req.getFullname() == null || req.getPassword() == null) {
			return HttpStatus.BAD_REQUEST;
		}

		if (userRepository.findByEmail(req.getEmail()) == null) {
			User user = new User();
			user.setEmail(req.getEmail());
			user.setActive(true);
			user.setPassword(passwordEncoder.encode(req.getPassword()));
			user.setFullname(req.getFullname());
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleService.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);
			return HttpStatus.CREATED;
		} else {
			return HttpStatus.CONFLICT;
		}
	}

	@Override
	public HttpStatus changeUserState(User req) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(req.getEmail());
		if (user == null) {
			return HttpStatus.BAD_REQUEST;
		} else {
			user.setActive(req.isActive());
			userRepository.save(user);
			return HttpStatus.OK;
		}
	}

	@Override
	public ResponseEntity<List<User>> getMemberList() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<User>>(userRepository.getMemberList(), HttpStatus.OK);
	}

}