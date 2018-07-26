package com.dxc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.models.Invoice;
import com.dxc.services.InvoiceService;
import com.dxc.services.UserService;

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
	public ResponseEntity<List<Invoice>> getInvoices() {

		return invoiceService.getInvoices();
	}

	@RequestMapping(value = "/invoice/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") int id) {
		return invoiceService.getInvoiceById(id);
	}
	
	@RequestMapping(value = "/invoice/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteInvoice(@PathVariable("id") int id) {
		return invoiceService.deleteInvoice(id);
	}
	
	@RequestMapping(value = "/invoice/update", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> updateInvoice(@RequestBody Invoice in) {

		return new ResponseEntity<Void>(invoiceService.updateInvoice(in));
	}

}
