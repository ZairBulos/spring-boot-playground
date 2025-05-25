package com.zair.business.services.impl;

import com.zair.api.dtos.request.OrderRequestDto;
import com.zair.api.dtos.response.OrderResponseDto;
import com.zair.business.events.publishers.OrderEventPublisher;
import com.zair.data.entities.OrderEntity;
import com.zair.data.repositories.OrderRepository;
import com.zair.business.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public OrderResponseDto create(OrderRequestDto orderRequestDto) {
        OrderEntity order = orderRepository.save(orderRequestDto.toEntity());
        orderEventPublisher.publishOrderPlaced(order);
        return OrderResponseDto.fromEntity(order);
    }
}
