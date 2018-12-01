package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query("select p from Product p where p.merchantId = :merchantId")
    public List<Product> findPrdByMechantId(@Param("merchantId") Integer merchantId);
	
	@Query("select p from Product p where p.tag LIKE :keyword")
    public List<Product> findPrdByKeyword(@Param("keyword") String keyword);

}
