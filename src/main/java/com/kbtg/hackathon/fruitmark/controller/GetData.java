package com.kbtg.hackathon.fruitmark.controller;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.dao.ProductRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;
import com.kbtg.hackathon.fruitmark.entity.Product;

@RestController
public class GetData {
	
	@Autowired
	MerchantRepository merchantRepo;
	
	@Autowired
	ProductRepository prdRepo;
	
	@RequestMapping("/getmerchant")
	public String getMerchant() {
		System.out.println("> getMerchant");
		Iterable<Merchant> list = merchantRepo.findAll();
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
		List<Merchant> list = merchantRepo.findByMerchantName("%เธ—เธธเน€เธฃเธตเธขเธ�%");
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
		List<Product> list = prdRepo.findPrdByMechantId(1);
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
		List<Product> list = prdRepo.findPrdByKeyword("ทุเรียนไปหน่อย");
		if (list != null) {
			for (Product p : list) {
				System.out.println("> " + ReflectionToStringBuilder.toString(p));
			}
		} else {
			System.out.print("Empty!");
		}
		
		return "Executed!";
	}
	
}
