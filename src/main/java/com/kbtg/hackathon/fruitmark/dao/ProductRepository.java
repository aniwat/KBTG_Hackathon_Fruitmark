package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kbtg.hackathon.fruitmark.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query("select p from Product p where p.merchantId = :merchantId")
    public List<Product> findPrdByMechantId(Integer merchantId);
	
	@Query("select p from Product p where p.productName LIKE :keyword")
    public List<Product> findPrdByKeyword(String keyword);

}
