package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kbtg.hackathon.fruitmark.entity.Merchant;

public interface MerchantRepository extends CrudRepository<Merchant, Integer> {

	/*@Query("SELECT * FROM MERCHANT;")*/
	List<Merchant> listMerchant();
	
}
