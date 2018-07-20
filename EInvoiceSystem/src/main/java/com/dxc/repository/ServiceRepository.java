package com.dxc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dxc.models.Service;
import com.dxc.models.User;


public interface ServiceRepository extends CrudRepository<Service, Integer>{
	List <Service> findByUser(User user);
}
