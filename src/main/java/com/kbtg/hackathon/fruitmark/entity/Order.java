package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer orderId;
	private Integer customerId;
	private Integer orderStatus;
	private Timestamp updateDate;
	
	public Order() {
		super();
	}

	public Order(Integer orderId, Integer customerId, Integer orderStatus, Timestamp updateDate) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.updateDate = updateDate;
	}

	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
