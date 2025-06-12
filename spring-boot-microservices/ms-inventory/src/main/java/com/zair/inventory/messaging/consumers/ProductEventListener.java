package com.zair.inventory.messaging.consumers;

import com.zair.inventory.messaging.config.RabbitMQConfig;
import com.zair.inventory.messaging.events.ProductCreatedEvent;
import com.zair.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventListener {
    private final InventoryService service;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleProductCreated(ProductCreatedEvent event) {
        service.create(event.sku(), event.quantity());
    }
}
