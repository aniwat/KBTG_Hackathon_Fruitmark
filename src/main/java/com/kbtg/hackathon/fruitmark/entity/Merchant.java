package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "merchant_id")
	private Integer merchantId;
	
	private String merchantName;
	
	private String merchantImg;
	
	private String merchantCover;
	
	private Integer rating;
	
	private Timestamp updateDate;
	
	public Merchant() {
		super();
	}
	
	public Merchant(Integer merchantId, String merchantName, String merchantImg, String merchantCover, Integer rating, Timestamp updateDate) {
		super();
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.merchantImg = merchantImg;
		this.merchantCover = merchantCover;
		this.rating = rating;
		this.updateDate = updateDate;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}
	
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	public String getMerchantName() {
		return merchantName;
	}
	
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getMerchantImg() {
		return merchantImg;
	}
	
	public void setMerchantImg(String merchantImg) {
		this.merchantImg = merchantImg;
	}
	
	public String getMerchantCover() {
		return merchantCover;
	}
	
	public void setMerchantCover(String merchantCover) {
		this.merchantCover = merchantCover;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
