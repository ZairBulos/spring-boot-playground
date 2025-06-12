package com.zair.inventory.service;

import com.zair.inventory.api.dtos.InventoryResponseDto;

public interface InventoryService {
    InventoryResponseDto read(String sku);
    InventoryResponseDto increase(String sku, Integer quantity);
    InventoryResponseDto decrease(String sku, Integer quantity);
    void create(String sku, Integer quantity);
}
