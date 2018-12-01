package com.kbtg.hackathon.fruitmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;

@RestController
public class DatabaseController {
	
	@Autowired
	MerchantRepository repo;
	
	@RequestMapping("/database")
	public String index() {
		System.out.println("Call DatabaseController");
		
		Iterable<Merchant> list = repo.findAll();
		if (list != null) {
			System.out.print("list != null");
			for (Merchant m : list) {
				System.out.println("m.getMerchantName() = " + m.getMerchantName());
				System.out.println("m.getMerchantId() = " + m.getMerchantId());
			}
		} else {
			System.out.print("list == null");
		}
		
		return "Database!";
	}
	
}
