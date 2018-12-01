package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private Integer merchantId;
	private String productName;
	private String productInfo;
	private String productImg;
	private String productUnit;
	private String pricePerUnit;
	private Integer rating;
	private String recommend;
	private Integer productStatus;
	private String tag;
	private Timestamp update_date;

	public Product() {
		super();
	}

	public Product(Integer productId, Integer merchantId, String productName, String productInfo, String productImg,
			String productUnit, String pricePerUnit, Integer rating, String recommend, Integer productStatus,
			String tag, Timestamp update_date) {
		super();
		this.productId = productId;
		this.merchantId = merchantId;
		this.productName = productName;
		this.productInfo = productInfo;
		this.productImg = productImg;
		this.productUnit = productUnit;
		this.pricePerUnit = pricePerUnit;
		this.rating = rating;
		this.recommend = recommend;
		this.productStatus = productStatus;
		this.tag = tag;
		this.update_date = update_date;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Timestamp getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}

}
