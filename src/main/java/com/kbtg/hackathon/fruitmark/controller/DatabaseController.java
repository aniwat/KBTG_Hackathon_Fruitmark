package com.kbtg.hackathon.fruitmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.dao.ProductRepository;
import com.kbtg.hackathon.fruitmark.entity.Product;

@RestController
public class DatabaseController {
	
	@Autowired
	MerchantRepository merchantRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping("/database")
	public String index() {
		System.out.println("Call DatabaseController");
		
		Iterable<Product> list = productRepo.findPrdByKeyword("%" + "น้ำหอม" + "%");
		if (list != null) {
			System.out.print("list != null");
			for (Product p : list) {
				System.out.println("p.getProductName() = " + p.getProductName());
				System.out.println("p.getPricePerUnit() = " + p.getPricePerUnit());
				System.out.println("p.getProductImg() = " + p.getProductImg());
			}
		} else {
			System.out.print("list == null");
		}
		
		// Iterable<Merchant> list = merchantRepo.findAll();
		// if (list != null) {
		// System.out.print("list != null");
		// for (Merchant m : list) {
		// System.out.println("m.getMerchantName() = " + m.getMerchantName());
		// System.out.println("m.getMerchantId() = " + m.getMerchantId());
		// }
		// } else {
		// System.out.print("list == null");
		// }
		
		return "Database!";
	}
	
}
