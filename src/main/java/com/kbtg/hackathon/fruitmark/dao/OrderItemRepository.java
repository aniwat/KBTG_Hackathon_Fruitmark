package com.kbtg.hackathon.fruitmark.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.OrderItem;

@Repository
public interface OrderItemRepository  extends CrudRepository<OrderItem, Integer>{

	@Query("select i from OrderItem i where i.orderId = :orderId")
    public List<OrderItem> findByOrderId(@Param("orderId") Integer orderId);
}
