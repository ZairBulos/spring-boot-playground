package com.zair.business.services;

import com.zair.api.dtos.request.OrderRequestDto;
import com.zair.api.dtos.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto create(OrderRequestDto orderRequestDto);
}
