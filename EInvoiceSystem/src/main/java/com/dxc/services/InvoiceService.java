package com.dxc.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import com.dxc.models.Invoice;

public interface InvoiceService {

	HttpStatus createInvoice(@RequestBody Invoice invoice);
}
