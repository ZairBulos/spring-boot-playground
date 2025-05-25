package com.zair.business.services.impl;

import com.zair.business.services.InventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Override
    public void updateInventory(String sku, Integer quantity) {
        System.out.println("Deducting " + quantity + " units from inventory for SKU: " + sku);
    }
}
