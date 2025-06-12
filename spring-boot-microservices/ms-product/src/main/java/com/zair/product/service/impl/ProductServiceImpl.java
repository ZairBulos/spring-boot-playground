package com.zair.product.service.impl;

import com.zair.product.api.dtos.ProductRequestDto;
import com.zair.product.api.dtos.ProductResponseDto;
import com.zair.product.data.entities.ProductEntity;
import com.zair.product.data.repositories.ProductRepository;
import com.zair.product.messaging.producers.ProductPublisher;
import com.zair.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductPublisher publisher;

    @Override
    public ProductResponseDto read(String sku) {
        return repository
                .findBySku(sku)
                .map(ProductResponseDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Product not found " + sku));
    }

    @Override
    @Transactional
    public ProductResponseDto create(ProductRequestDto productRequest) {
        assertNotExists(productRequest.sku());
        ProductEntity product = repository.save(new ProductEntity(null, productRequest.sku(), productRequest.name()));
        publisher.publishProductCreated(productRequest.sku(), productRequest.quantity());

        return ProductResponseDto.fromEntity(product);
    }

    private void assertNotExists(String sku) {
        if (repository.findBySku(sku).isPresent()) {
            throw new RuntimeException("Product already exists " + sku);
        }
    }
}
