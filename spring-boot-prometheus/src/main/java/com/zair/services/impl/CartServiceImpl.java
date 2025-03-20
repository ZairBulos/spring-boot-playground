package com.zair.services.impl;

import com.zair.dtos.CartRequestDTO;
import com.zair.dtos.CartResponseDTO;
import com.zair.exceptions.CheckoutException;
import com.zair.services.CartService;
import com.zair.services.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final MetricsService metricsService;
    private final List<String> items = new ArrayList<>();

    @Override
    public CartResponseDTO add(CartRequestDTO cart) {
        String item = cart.getItem();

        items.add(item);
        metricsService.incrementItemsAdded();
        return CartResponseDTO.builder().message(item + " added to the cart!").items(items).build();
    }

    @Override
    public String checkout() {
        if (items.isEmpty()) {
            metricsService.incrementCheckoutFailure();
            throw new CheckoutException("Cart is empty! Add products before checkout.");
        }

        items.clear();
        metricsService.incrementCheckoutSuccess();
        return "Checkout completed successfully";
    }
}
