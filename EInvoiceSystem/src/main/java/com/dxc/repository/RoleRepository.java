package com.dxc.repository;

import org.springframework.data.repository.CrudRepository;

import com.dxc.models.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
}
