package com.kbtg.hackathon.fruitmark.controller;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.kbtg.hackathon.fruitmark.dao.CustomerRepository;
import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.dao.OrderItemRepository;
import com.kbtg.hackathon.fruitmark.dao.ProductRepository;
import com.kbtg.hackathon.fruitmark.entity.Customer;
import com.kbtg.hackathon.fruitmark.entity.Merchant;
import com.kbtg.hackathon.fruitmark.entity.OrderItem;
import com.kbtg.hackathon.fruitmark.entity.Product;

@RestController
public class GetData {

	@Autowired
	MerchantRepository mercRepo;
	
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@RequestMapping("/getmerchant")
	public String getMerchant() {
		System.out.println("> getMerchant");
		Iterable<Merchant> list = mercRepo.findAll();
		//TODO Use as this line below
		List<Merchant> myList = Lists.newArrayList(list);
		if (list != null) {
			for (Merchant m : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(m));
			}
		} else {
			System.out.print("Empty!");
		}
		
		return "Executed!";
	}
	
	
	@RequestMapping("/getmerchantbyname")
	public String getMerchantByName() {
		System.out.println("> getMerchantByName.");
		//TODO Use as this line below
		List<Merchant> list = mercRepo.findByMerchantName("%ทุเรียน%");
		if (list != null) {
			for (Merchant m : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(m));
			}
		} else {
			System.out.print("Empty!");
		}
		
		return "Executed!";
	}
	
	@RequestMapping("/getprdbymerchant")
	public String getPrdByMerchantId() {
		System.out.println("> getPrdByMerchantId.");
		//TODO Use as this line below
		List<Product> list = prodRepo.findPrdByMechantId(1);
		if (list != null) {
			for (Product p : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(p));
			}
		} else {
			System.out.print("Empty!");
		}
		
		return "Executed!";
	}
	
	@RequestMapping("/getprdbykeyword")
	public String getPrdByKeyword() {
		System.out.println("> getPrdByKeyword.");
		//TODO Use as this line below
		List<Product> list = prodRepo.findPrdByKeyword("ทุเรียน");
		if (list != null) {
			for (Product p : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(p));
			}
		} else {
			System.out.print("Empty!");
		}
		
		return "Executed!";
	}
	
	@RequestMapping("/getcustomer")
	public String getCustomer() {
		System.out.println("> getCustomer");
		//TODO Use as this line below
		List<Customer> list = custRepo.findByLineId("Cherprang");
		if (list != null) {
			for (Customer m : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(m));
			}
		} else {
			System.out.print("Empty!");
		}
		
		return "Executed!";
	}
	
	@RequestMapping("/getorderitem")
	public String getOrderItem() {
		
		System.out.println("> getCustomer");
		//TODO Use as this line below
		List<OrderItem> list = orderItemRepo.findByOrderId(1);
		if (list != null) {
			for (OrderItem m : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(m));
			}
		} else {
			System.out.print("Empty!");
		}
		return "Executed!";
	}
	
	@RequestMapping("/cartOrder")
	public String cartOrder() {
		
		return null;
	}
	
	@RequestMapping("/saveOrder")
	public String saveOrder() {
		
		
		return null;
	}
	
}
