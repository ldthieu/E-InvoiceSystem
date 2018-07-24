package com.dxc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.models.Invoice;
import com.dxc.models.User;
import com.dxc.services.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@RequestMapping(value = "/invoice/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> register(@RequestBody Invoice in) {
		
		return new ResponseEntity<Void>(invoiceService.createInvoice(in));
//		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
