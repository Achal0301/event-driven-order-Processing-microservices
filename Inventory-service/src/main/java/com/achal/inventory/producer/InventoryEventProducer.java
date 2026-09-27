package com.achal.inventory.producer;

import com.achal.events.InventoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventProducer {

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    public void sendInventoryEvent(String topic, InventoryEvent event) {
        kafkaTemplate.send(topic, event.getOrderId(), event);
    }
}

