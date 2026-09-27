package com.achal.payment.service;

import com.achal.events.InventoryEvent;
import com.achal.events.PaymentEvent;
import com.achal.payment.model.Payment;
import com.achal.payment.producer.PaymentEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentEventProducer producer;

    public void processPayment(InventoryEvent event) {
        if ("RESERVED".equals(event.getStatus())) {

            // Simulate payment success
            producer.sendPaymentEvent(
                    "payment-success",
                    new PaymentEvent(event.getOrderId(), "SUCCESS" , event.getAmount())
            );

        } else {

            producer.sendPaymentEvent(
                    "payment-failed",
                    new PaymentEvent(event.getOrderId(), "FAILED" , event.getAmount())
            );
        }
    }
}

