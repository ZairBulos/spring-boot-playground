package com.zair.product.api.dtos;

import com.zair.product.data.entities.ProductEntity;

public record ProductResponseDto(String id, String sku, String name) {

    public static ProductResponseDto fromEntity(ProductEntity entity) {
        return new ProductResponseDto(entity.getId(), entity.getSku(), entity.getName());
    }
}
