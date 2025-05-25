package com.zair.api.dtos.response;

import com.zair.data.entities.OrderEntity;

import java.time.LocalDateTime;

public record OrderResponseDto(String id, String sku, Integer quantity, String userEmail, LocalDateTime orderDate) {
    public static OrderResponseDto fromEntity(OrderEntity order) {
        return new OrderResponseDto(order.getId(), order.getSku(), order.getQuantity(), order.getUserEmail(), order.getOrderDate());
    }
}
