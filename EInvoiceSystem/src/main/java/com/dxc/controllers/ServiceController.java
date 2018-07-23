package com.dxc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@RestController
public class ServiceController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	ServiceService serviceService;
	
	public User getUser(@RequestBody Service ser, UriComponentsBuilder ucBuilder){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = new User();
		user = userRepository.findByEmail(email);
		return user;
	}
	
	@RequestMapping(value = "/service", method = RequestMethod.POST)
	@ResponseBody
	public List<Service> getListService(@RequestBody Service ser, UriComponentsBuilder ucBuilder){
		List<Service> service = serviceRepository.findByUser(getUser(ser, ucBuilder));
		return service;
	}
	
	
	@RequestMapping(value = "/service/create", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> createService(@RequestBody Service ser, UriComponentsBuilder ucBuilder) {
		
		if (ser.getServiceName()==null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if(serviceRepository.findByServiceName(ser.getServiceName())==null){
			
			
			Service service = new Service();
			service.setServiceName(ser.getServiceName());
			service.setUser(getUser(ser,ucBuilder));
			serviceService.saveService(service);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/service/{id}").buildAndExpand(service.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/service/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> updateService(@RequestBody Service ser, UriComponentsBuilder ucBuilder) {
		
		if (ser.getServiceName()==null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if(serviceRepository.findByServiceName(ser.getServiceName())!=null){
			
			
			Service service = new Service();
			service.setServiceName(ser.getServiceName());
			service.setUser(getUser(ser,ucBuilder));
			serviceService.updateService(service);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/service/{id}").buildAndExpand(service.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
		
		
}
