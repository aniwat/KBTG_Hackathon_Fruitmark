package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

public class OrderItem {
	
	private Integer orderItemId;
	private Integer orderId;
	private Integer productId;
	private Integer orderAmt;
	private Timestamp orderDate;
	
	public Integer getOrderItemId() {
		return orderItemId;
	}
	
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getOrderAmt() {
		return orderAmt;
	}
	
	public void setOrderAmt(Integer orderAmt) {
		this.orderAmt = orderAmt;
	}
	
	public Timestamp getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	
}
