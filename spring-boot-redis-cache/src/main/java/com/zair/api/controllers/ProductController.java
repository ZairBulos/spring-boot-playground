package com.zair.api.controllers;

import com.zair.api.dtos.PageResponseDto;
import com.zair.api.dtos.ProductResponseDto;
import com.zair.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponseDto> read(@PathVariable String sku) {
        return ResponseEntity.ok(productService.read(sku));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponseDto<ProductResponseDto>> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        return ResponseEntity.ok(productService.search(page, size, keyword));
    }
}
