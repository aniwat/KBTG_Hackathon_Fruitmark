package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

public class Customer {

	private Integer customerId;
	private String customerLineId;
	private Timestamp updateDate;

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
