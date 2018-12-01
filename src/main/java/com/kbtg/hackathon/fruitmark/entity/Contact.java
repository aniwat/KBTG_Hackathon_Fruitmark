package com.kbtg.hackathon.fruitmark.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private Integer contactId;
	private Integer ownerId;
	private Integer ownerType;
	private String address;
	private String location;
	private String contactNo;
	private String email;
	private String website;
	private Timestamp updateDate;
	
	public Contact() {
		super();
	}

	public Contact(Integer contactId, Integer ownerId, Integer ownerType, String address, String location,
			String contactNo, String email, String website, Timestamp updateDate) {
		super();
		this.contactId = contactId;
		this.ownerId = ownerId;
		this.ownerType = ownerType;
		this.address = address;
		this.location = location;
		this.contactNo = contactNo;
		this.email = email;
		this.website = website;
		this.updateDate = updateDate;
	}

	public Integer getContactId() {
		return contactId;
	}
	
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	public Integer getOwnerType() {
		return ownerType;
	}
	
	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}
