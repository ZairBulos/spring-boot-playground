package com.zair.inventory.messaging.events;

public record ProductCreatedEvent(String sku, Integer quantity) {
}
