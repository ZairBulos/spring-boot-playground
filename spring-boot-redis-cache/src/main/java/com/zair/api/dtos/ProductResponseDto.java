package com.zair.api.dtos;

import com.zair.data.entities.ProductEntity;

import java.math.BigDecimal;

public record ProductResponseDto(String sku, String name, BigDecimal price) {
    public static ProductResponseDto fromEntity(ProductEntity product) {
        return new ProductResponseDto(product.getSku(), product.getName(), product.getPrice());
    }
}
