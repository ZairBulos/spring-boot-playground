package com.zair.api.dtos.request;

import com.zair.data.entities.OrderEntity;

public record OrderRequestDto(String sku, Integer quantity, String userEmail) {
    public OrderEntity toEntity() {
        return new OrderEntity(sku, quantity, userEmail);
    }
}
