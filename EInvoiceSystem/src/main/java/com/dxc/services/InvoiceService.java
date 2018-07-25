package com.dxc.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.dxc.models.Invoice;

public interface InvoiceService {

	HttpStatus createInvoice(@RequestBody Invoice invoice);
	
	ResponseEntity<List<Invoice>> getInvoices();
	
	ResponseEntity<Invoice> getInvoiceById(int id);
	
	ResponseEntity<Void> deleteInvoice(int id);
	
	ResponseEntity<Void> updateInvoice(Invoice invoice);
}
