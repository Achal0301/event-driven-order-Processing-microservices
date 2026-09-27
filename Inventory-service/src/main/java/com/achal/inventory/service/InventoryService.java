package com.achal.inventory.service;

import com.achal.events.InventoryEvent;
import com.achal.events.OrderCreatedEvent;
import com.achal.inventory.model.Inventory;
import com.achal.inventory.producer.InventoryEventProducer;
import com.achal.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;
    private final InventoryEventProducer producer;

    @KafkaListener(topics = "order-created-topic", groupId = "inventory-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("Received order: " + event);
        processOrder(event);

    }


    public void processOrder(OrderCreatedEvent event) {

        Inventory inventory = repository.findByProductName(event.getProductName())
                .orElse(null);

        if (inventory != null && inventory.getQuantity() >= event.getQuantity()) {
            inventory.setQuantity(inventory.getQuantity() - event.getQuantity());
            repository.save(inventory);

            producer.sendInventoryEvent(
                    "inventory-reserved",
                    new InventoryEvent(event.getOrderId(), "RESERVED", event.getPrice())
            );
        } else {
            producer.sendInventoryEvent(
                    "inventory-failed",
                    new InventoryEvent(event.getOrderId(), "FAILED",event.getPrice())
            );
        }
    }
}

