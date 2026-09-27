package com.achal.payment.producer;

import com.achal.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentEvent(String topic, PaymentEvent event) {
        kafkaTemplate.send(topic, event);
        System.out.println("Payment event sent to " + topic + " -> " + event);
    }
}
