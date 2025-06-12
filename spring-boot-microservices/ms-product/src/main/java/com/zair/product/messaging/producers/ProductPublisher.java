package com.zair.product.messaging.producers;

import com.zair.product.messaging.config.RabbitMQConfig;
import com.zair.product.messaging.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publishProductCreated(String sku, Integer quantity) {
        ProductCreatedEvent event = new ProductCreatedEvent(sku, quantity);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
    }
}
