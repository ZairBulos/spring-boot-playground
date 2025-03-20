package com.zair.controllers;

import com.zair.dtos.CartRequestDTO;
import com.zair.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CartRequestDTO item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(item));
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout() {
        return ResponseEntity.status(HttpStatus.OK).body(service.checkout());
    }
}
