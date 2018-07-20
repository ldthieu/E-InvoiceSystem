package com.dxc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dxc.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);
	User findById(int id);
}
