package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Integer customerId;
	private String customerLineId;
	private Timestamp updateDate;
	
	public Customer() {
		super();
	}

	public Customer(Integer customerId, String customerLineId, Timestamp updateDate) {
		super();
		this.customerId = customerId;
		this.customerLineId = customerLineId;
		this.updateDate = updateDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerLineId() {
		return customerLineId;
	}
	
	public void setCustomerLineId(String customerLineId) {
		this.customerLineId = customerLineId;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
