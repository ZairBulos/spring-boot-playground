package com.zair.services;

import com.zair.api.dtos.PageResponseDto;
import com.zair.api.dtos.ProductResponseDto;

public interface ProductService {
    ProductResponseDto read(String sku);
    PageResponseDto<ProductResponseDto> search(int page, int size, String keyword);
}
