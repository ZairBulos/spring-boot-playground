package com.zair.services;

import com.zair.dtos.CartRequestDTO;
import com.zair.dtos.CartResponseDTO;

public interface CartService {
    CartResponseDTO add(CartRequestDTO cart);
    String checkout();
}
