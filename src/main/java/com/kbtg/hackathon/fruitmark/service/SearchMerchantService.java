package com.kbtg.hackathon.fruitmark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;

@RestController
public class SearchMerchantService {
	
	@Autowired
	MerchantRepository repo;
	
	public Iterable<Merchant> findAll() {
		Iterable<Merchant> list = repo.findAll();
		return list;
	}
	
	public Iterable<Merchant> searchByName(String name) {
		return repo.findByMerchantName(name);
	}
	
	public String searchByMerchant(String name) {
		return "";
	}
	
}
