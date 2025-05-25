package com.zair.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String sku;

    private Integer quantity;

    private String userEmail;

    private LocalDateTime orderDate;

    public OrderEntity(String sku, Integer quantity, String userEmail) {
        this.sku = sku;
        this.quantity = quantity;
        this.userEmail = userEmail;
    }

    @PrePersist
    private void onCreate() {
        orderDate = LocalDateTime.now();
    }
}
