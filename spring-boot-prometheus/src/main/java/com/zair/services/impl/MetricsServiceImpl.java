package com.zair.services.impl;

import com.zair.services.MetricsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final Counter cartItemsAdded;
    private final Counter cartCheckoutSuccess;
    private final Counter cartCheckoutFailure;

    public MetricsServiceImpl(MeterRegistry registry) {
        this.cartItemsAdded = Counter
                .builder("cart_items_added")
                .description("Number of times add to cart is called")
                .register(registry);
        this.cartCheckoutSuccess = Counter
                .builder("cart_checkout_success")
                .description("Number of times checkout is successful")
                .register(registry);
        this.cartCheckoutFailure = Counter
                .builder("cart_checkout_failure")
                .description("Number of times checkout fails")
                .register(registry);
    }

    @Override
    public void incrementItemsAdded() {
        cartItemsAdded.increment();
    }

    @Override
    public void incrementCheckoutSuccess() {
        cartCheckoutSuccess.increment();
    }

    @Override
    public void incrementCheckoutFailure() {
        cartCheckoutFailure.increment();
    }
}
