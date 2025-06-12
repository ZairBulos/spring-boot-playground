package com.zair.product.api.controllers;

import com.zair.product.api.dtos.ProductRequestDto;
import com.zair.product.api.dtos.ProductResponseDto;
import com.zair.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponseDto> read(@PathVariable String sku) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.read(sku));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(productRequest));
    }
}
