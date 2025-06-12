package com.zair.inventory.api.dtos;

import com.zair.inventory.data.entities.InventoryEntity;

public record InventoryResponseDto(String id, String sku, Integer quantity) {

    public static InventoryResponseDto fromEntity(InventoryEntity entity) {
        return new InventoryResponseDto(entity.getId(), entity.getSku(), entity.getQuantity());
    }
}
