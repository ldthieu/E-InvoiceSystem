package com.dxc.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.models.Invoice;
import com.dxc.models.Service;
import com.dxc.models.User;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer>, JpaRepository<Invoice, Integer> {
	
	Invoice findByServiceAndUser(Service service, User user);
	
	List<Invoice> findByUser(User user);
	
	Invoice findById(int id);
	
	@Modifying
	@Transactional
	@Query("SELECT i FROM Invoice i, User u JOIN i.service s WHERE s.id = :serviceId and u.id=:userId and i.createdDate between :dateStart and :dateEnd")
	List<Invoice> getInvoiceByServiceAndDate(@Param("serviceId") int serviceId, @Param("userId") int userId, @Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
}
