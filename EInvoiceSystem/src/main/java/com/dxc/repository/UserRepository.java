package com.dxc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);
	User findById(int id);
	
	@Transactional
	@Modifying
	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.name='ROLE_MEMBER' AND u.id NOT IN (SELECT u.id FROM User u JOIN u.roles r WHERE r.name='ROLE_ADMIN')")
	List<User> getMemberList();
}
