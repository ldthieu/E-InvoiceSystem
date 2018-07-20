package com.dxc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.dxc.models.Service;
import com.dxc.models.User;
import com.dxc.repository.UserRepository;
import com.dxc.services.ServiceService;

@RestController
public class ServiceController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ServiceService serviceService;
	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public ResponseEntity<Void> createInvoice(@RequestBody Service service, UriComponentsBuilder ucBuilder) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = new User();
		user = userRepository.findByEmail(email);
		
		service.setUser(user);
		serviceService.saveService(service);
		
		

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/invoice/{id}").buildAndExpand(service.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
