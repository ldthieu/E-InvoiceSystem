package com.dxc.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;

import com.dxc.models.Invoice;
import com.dxc.models.Service;
import com.dxc.models.User;
import com.dxc.repository.InvoiceRepository;

@org.springframework.stereotype.Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	UserService userService;

	@Autowired
	ServiceService serviceService;

	@Override
	public HttpStatus createInvoice(Invoice invoice) {
		// TODO Auto-generated method stub

		if (invoice.getCustomerCode() == null || invoice.getInvoiceNo() == null || invoice.getService() == null
				|| invoice.getAmountOfMoney() < 0 || invoice.getVat() < 0) {
			return HttpStatus.BAD_REQUEST;
		}

		// check service exists
		Service service = serviceService.findById(invoice.getService().getId());
		if (service == null) {
			return HttpStatus.BAD_REQUEST;
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.findByEmail(email);

		// check duplication of monthly invoice
		if (service.isMonthly()) {
			if (invoiceRepository.findByServiceAndUser(service, user) != null) {
				return HttpStatus.CONFLICT;
			}
		}

		invoice.setTotalMoney(invoice.getAmountOfMoney() + invoice.getVat());
		invoice.setUser(user);
		invoice.setService(service);

		invoiceRepository.save(invoice);

		return HttpStatus.CREATED;
	}

	@Override
	public ResponseEntity<List<Invoice>> getInvoice() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.findByEmail(email);

		List<Invoice> list = null;
		if (user != null) {
			list = invoiceRepository.findByUser(user);
			return new ResponseEntity<List<Invoice>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Invoice>>(HttpStatus.UNAUTHORIZED);
		}
	}

}
