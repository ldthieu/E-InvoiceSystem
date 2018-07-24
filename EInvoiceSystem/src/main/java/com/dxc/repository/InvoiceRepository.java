package com.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.models.Invoice;
import com.dxc.models.Service;
import com.dxc.models.User;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer>, JpaRepository<Invoice, Integer> {

//	@Modifying
//	@Transactional
//	@Query("Delete From Service s where s.user.id = ?1")
//	int setByUserId(int userId);
	
	Invoice findByServiceAndUser(Service service, User user);
}
