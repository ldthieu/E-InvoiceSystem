package com.dxc.services;

import com.dxc.models.Service;


public interface ServiceService {

	void saveService(Service service);
	
	void updateService(Service service);
	
	void deleteServiceById(int id);
	
	Service findById(int id);
}
