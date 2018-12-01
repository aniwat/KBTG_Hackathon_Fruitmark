package com.kbtg.hackathon.fruitmark.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
