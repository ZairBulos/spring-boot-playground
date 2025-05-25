package com.zair.business.events.listeners;

import com.zair.business.events.models.OrderPlacedEvent;
import com.zair.business.services.MailService;
import com.zair.data.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final MailService mailService;

    @Async
    @EventListener
    public void sendOrderConfirmation(OrderPlacedEvent orderPlacedEvent) {
        OrderEntity order = orderPlacedEvent.getOrder();

        log.info("Sending order confirmation: {}", order);
        mailService.send(order.getUserEmail(), "Order Confirmation", "Your order has been placed successfully!");
    }
}
