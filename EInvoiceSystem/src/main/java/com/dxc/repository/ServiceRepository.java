package com.dxc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dxc.models.Service;
import com.dxc.models.User;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer>, JpaRepository<Service, Integer>{
	List <Service> findByUser(User user);
	Service findByServiceName(String serviceName);
}
