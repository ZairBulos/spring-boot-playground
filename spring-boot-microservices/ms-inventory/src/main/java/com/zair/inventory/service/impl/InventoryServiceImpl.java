package com.zair.inventory.service.impl;

import com.zair.inventory.api.dtos.InventoryResponseDto;
import com.zair.inventory.data.entities.InventoryEntity;
import com.zair.inventory.data.repositories.InventoryRepository;
import com.zair.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository repository;

    @Override
    public InventoryResponseDto read(String sku) {
        return repository
                .findBySku(sku)
                .map(InventoryResponseDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Product not found " + sku));
    }

    @Override
    @Transactional
    public InventoryResponseDto increase(String sku, Integer quantity) {
        InventoryEntity inventory = repository
                .findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found " + sku));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        repository.save(inventory);

        return InventoryResponseDto.fromEntity(inventory);
    }

    @Override
    public InventoryResponseDto decrease(String sku, Integer quantity) {
        InventoryEntity inventory = repository
                .findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found " + sku));
        if (inventory.getQuantity() < quantity) throw new RuntimeException("Stock insufficient");
        inventory.setQuantity(inventory.getQuantity() - quantity);
        repository.save(inventory);

        return InventoryResponseDto.fromEntity(inventory);
    }

    @Override
    @Transactional
    public void create(String sku, Integer quantity) {
        if (repository.findBySku(sku).isPresent()) return;
        repository.save(new InventoryEntity(null, sku, quantity));
    }
}
