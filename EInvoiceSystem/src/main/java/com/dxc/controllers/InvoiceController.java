package com.dxc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.models.Invoice;
import com.dxc.models.Service;
import com.dxc.models.User;
import com.dxc.services.InvoiceService;
import com.dxc.services.UserService;
import com.fasterxml.jackson.annotation.JsonBackReference;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/invoice/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createInvoice(@RequestBody Invoice in) {

		return new ResponseEntity<Void>(invoiceService.createInvoice(in));
	}

	@RequestMapping(value = "/invoice/get", method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<List<Invoice>> getInvoice() {

		return invoiceService.getInvoice();
	}

}
