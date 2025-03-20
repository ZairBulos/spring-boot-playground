package com.zair.services;

public interface MetricsService {
    void incrementItemsAdded();
    void incrementCheckoutSuccess();
    void incrementCheckoutFailure();
}
