package com.achal.order.service;

import com.achal.events.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.achal.order.config.KafkaTopicProperties;


@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    public OrderEventProducer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate,
            KafkaTopicProperties kafkaTopicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicProperties = kafkaTopicProperties;
    }


    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        String topic = kafkaTopicProperties.getTopicName();
        System.out.println("Sending event to topic: " + topic);

        kafkaTemplate.send(topic, event.getOrderId(), event).whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println(
                        "Message sent successfully. Topic=" +
                                result.getRecordMetadata().topic() +
                                ", Offset=" + result.getRecordMetadata().offset()
                );
            } else {
                System.err.println("Failed to send message: " + ex.getMessage());
            }
        });
    }
}
