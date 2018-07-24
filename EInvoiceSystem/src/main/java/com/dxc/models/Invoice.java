package com.dxc.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;
	
	@Column(name = "invoice_no", nullable = false)
	private String invoiceNo;
	
	@Column(name = "customer_code")
	private String customerCode;
	
	@Column(name = "amount_of_money", nullable = false)
	private double amountOfMoney;
	
	@Column(name = "vat", nullable = false)
	private double vat;
	
	@Basic(optional = false)
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public double getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}