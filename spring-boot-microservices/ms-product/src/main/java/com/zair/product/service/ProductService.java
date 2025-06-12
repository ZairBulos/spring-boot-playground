package com.zair.product.service;

import com.zair.product.api.dtos.ProductRequestDto;
import com.zair.product.api.dtos.ProductResponseDto;

public interface ProductService {
    ProductResponseDto read(String sku);
    ProductResponseDto create(ProductRequestDto productRequest);
}
