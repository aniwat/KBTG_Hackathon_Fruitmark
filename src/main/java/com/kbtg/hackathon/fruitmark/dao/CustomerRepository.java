package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	@Query("select c from Customer c where c.customerLineId = :customerLineId")
    public List<Customer> findByLineId(@Param("customerLineId") String customerLineId);
}
