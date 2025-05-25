package com.zair.business.events.publishers;

import com.zair.business.events.models.OrderPlacedEvent;
import com.zair.data.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishOrderPlaced(OrderEntity orderEntity) {
        log.info("Publishing OrderPlacedEvent for order: {}", orderEntity);
        eventPublisher.publishEvent(new OrderPlacedEvent(this, orderEntity));
    }
}
