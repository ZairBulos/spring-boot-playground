package com.zair.business.events.listeners;

import com.zair.business.events.models.OrderPlacedEvent;
import com.zair.business.services.InvoiceService;
import com.zair.data.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceService invoiceService;

    @Async
    @EventListener
    public void generateInvoice(OrderPlacedEvent orderPlacedEvent) {
        OrderEntity order = orderPlacedEvent.getOrder();

        log.info("Generating invoice for order: {}", order);
        invoiceService.create(order.getId());
    }
}
