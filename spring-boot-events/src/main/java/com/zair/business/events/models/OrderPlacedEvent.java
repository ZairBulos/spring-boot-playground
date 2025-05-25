package com.zair.business.events.models;

import com.zair.data.entities.OrderEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderPlacedEvent extends ApplicationEvent {
    private final OrderEntity order;

    public OrderPlacedEvent(Object source, OrderEntity order) {
        super(source);
        this.order = order;
    }
}
