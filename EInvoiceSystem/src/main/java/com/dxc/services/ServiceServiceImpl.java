package com.dxc.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.models.Service;
import com.dxc.repository.ServiceRepository;

public class ServiceServiceImpl implements ServiceService {

	@Autowired
	ServiceRepository serviceRepository;
	
	@Override
	public void saveService(Service service) {
		// TODO Auto-generated method stub
		serviceRepository.save(service);
	}

	@Override
	public void updateService(Service service) {
		// TODO Auto-generated method stub
		serviceRepository.saveAndFlush(service);
		
	}

	@Override
	public void deleteServiceById(int id) {
		// TODO Auto-generated method stub
		serviceRepository.deleteById(id);
	}

}
