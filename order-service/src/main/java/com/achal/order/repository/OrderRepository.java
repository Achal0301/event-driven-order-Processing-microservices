package com.achal.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.achal.order.model.Order;

public interface OrderRepository extends MongoRepository<Order ,String > {

}
