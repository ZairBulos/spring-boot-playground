package com.zair.business.events.listeners;

import com.zair.business.events.models.OrderPlacedEvent;
import com.zair.business.services.InventoryService;
import com.zair.data.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryListener {
    private final InventoryService inventoryService;

    @Async
    @EventListener
    public void updateInventory(OrderPlacedEvent orderPlacedEvent) {
        OrderEntity order = orderPlacedEvent.getOrder();

        log.info("Updating inventory for order: {}", order);
        inventoryService.updateInventory(order.getSku(), order.getQuantity());
    }
}
