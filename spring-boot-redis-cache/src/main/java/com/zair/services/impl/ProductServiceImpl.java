package com.zair.services.impl;

import com.zair.api.dtos.PageResponseDto;
import com.zair.api.dtos.ProductResponseDto;
import com.zair.data.entities.ProductEntity;
import com.zair.data.repositories.ProductRepository;
import com.zair.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static final String PRODUCTS_CACHE = "products";
    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = PRODUCTS_CACHE, key = "#sku")
    public ProductResponseDto read(String sku) {
        return productRepository
                .findBySku(sku)
                .map(ProductResponseDto::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException(sku));
    }

    @Override
    @Cacheable(value = PRODUCTS_CACHE, key = "#keyword + #page + #size")
    public PageResponseDto<ProductResponseDto> search(int page, int size, String keyword) {
        Page<ProductEntity> pageProduct;

        if (keyword != null && !keyword.isEmpty()) {
            pageProduct = productRepository.findByNameContaining(keyword, PageRequest.of(page, size));
        } else {
            pageProduct = productRepository.findAll(PageRequest.of(page, size));
        }

        return PageResponseDto.from(pageProduct.map(ProductResponseDto::fromEntity));
    }
}
