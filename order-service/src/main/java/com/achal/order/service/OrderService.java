package com.achal.order.service;


import com.achal.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achal.order.model.Order;
import com.achal.order.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderEventProducer producer;
	
	public Order createOrder(Order order) {
        order.setStatus("CREATED");
        Order savedOrder =orderRepo.save(order);
        producer.publishOrderCreatedEvent(
                new OrderCreatedEvent(
                    savedOrder.getId(),
                    savedOrder.getProductName(),
                    savedOrder.getQuantity(),
                    savedOrder.getPrice()
                )
            );
        return savedOrder;

    }
	
	public Order getOrderById(String id) {
	    return orderRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
	}


}
