package com.dxc.repository;

import org.springframework.data.repository.CrudRepository;

import com.dxc.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);
}
