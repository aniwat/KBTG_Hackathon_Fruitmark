package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
	
	// @Query(nativeQuery = true, value = "SELECT * FROM merchant WHERE merchant_name LIKE '%?1%'")
	@Query(nativeQuery = true, value = "SELECT * FROM merchant")
	List<Merchant> findByMerchantName(String name);
	
}
