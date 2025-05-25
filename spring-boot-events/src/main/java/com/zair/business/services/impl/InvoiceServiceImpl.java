package com.zair.business.services.impl;

import com.zair.business.services.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Override
    public void create(String orderId) {
        System.out.println("Create invoice for order: " + orderId);
    }
}
