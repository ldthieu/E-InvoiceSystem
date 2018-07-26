package com.dxc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dxc.models.Service;
import com.dxc.models.User;
import com.dxc.repository.ServiceRepository;
import com.dxc.repository.UserRepository;
import com.dxc.services.ServiceService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@RestController
public class ServiceController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ServiceService serviceService;

	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = new User();
		user = userRepository.findByEmail(email);
		return user;
	}

	public User getAdminUser() {
		User admin = userRepository.findByEmail("admin@gmail.com");
		return admin;
	}

	@RequestMapping(value = "/service/get", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<List<Service>> getListService() {
		List<Service> service = serviceRepository.findByUserOrUser(getAdminUser(), getUser());
		if (service == null) {
			return new ResponseEntity<List<Service>>(service, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Service>>(service, HttpStatus.OK);
	}

	@RequestMapping(value = "/service/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpStatus createService(@RequestBody Service ser, UriComponentsBuilder ucBuilder) {

		if (ser.getServiceName() == null) {
			return HttpStatus.BAD_REQUEST;
		}

		if (serviceRepository.findByServiceNameAndUser(ser.getServiceName(), getUser()) == null) {

//			Service service = new Service();
//			service.setServiceName(ser.getServiceName());
			ser.setUser(getUser());
//			service.setMonthly(ser.isMonthly());
			serviceService.saveService(ser);

			return HttpStatus.CREATED;
		} else {
			return HttpStatus.CONFLICT;
		}
	}

	@RequestMapping(value = "/service/update", method = RequestMethod.PUT)
	@ResponseBody
	public HttpStatus updateService(@RequestBody Service ser, UriComponentsBuilder ucBuilder) {

		if (ser.getServiceName() == null) {
			return HttpStatus.NO_CONTENT;
		}

		Service service = serviceRepository.findByIdAndUser(ser.getId(),getUser());

		if (service != null) {

			service.setServiceName(ser.getServiceName());
			service.setUser(getUser());
			service.setMonthly(ser.isMonthly());
			serviceService.updateService(service);

			return HttpStatus.ACCEPTED;
		} else {
			return HttpStatus.NOT_MODIFIED;
		}

	}

	@RequestMapping(value = "/service/{serviceId}", method = RequestMethod.DELETE)
	@ResponseBody
	public HttpStatus deleteService(@PathVariable("serviceId") int serviceId) {
		List<Service> service = serviceRepository.findByUser(getUser());
		if(service.contains(serviceRepository.findById(serviceId))) {
			serviceService.deleteServiceById(serviceId);
			return HttpStatus.OK;
		}
		else {
			return HttpStatus.NOT_ACCEPTABLE;
		}
		

	}

}
