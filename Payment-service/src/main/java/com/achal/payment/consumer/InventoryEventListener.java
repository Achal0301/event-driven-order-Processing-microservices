package com.achal.payment.consumer;

import com.achal.events.InventoryEvent;
import com.achal.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventListener {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = {"inventory-reserved", "inventory-failed"},
            groupId = "payment-group"
    )
    public void consumeInventoryEvent(InventoryEvent event) {
        paymentService.processPayment(event);
    }
}

