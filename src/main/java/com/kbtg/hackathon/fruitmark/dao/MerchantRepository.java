package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
	
	@Query("select m from Merchant m where m.merchantName LIKE :merchantName")
    public List<Merchant> findByMerchantName(String merchantName);
	
}
