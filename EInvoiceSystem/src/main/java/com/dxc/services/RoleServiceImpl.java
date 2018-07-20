package com.dxc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.models.Role;
import com.dxc.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}

}
