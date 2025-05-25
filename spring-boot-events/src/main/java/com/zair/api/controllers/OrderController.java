package com.zair.api.controllers;

import com.zair.api.dtos.request.OrderRequestDto;
import com.zair.api.dtos.response.OrderResponseDto;
import com.zair.business.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto order) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.create(order));
    }
}
