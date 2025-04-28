package com.zair.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String barcode;
    private String description;
    private BigDecimal retailPrice;
    private Integer stock;
    private Boolean discontinued;
    private String providerCompany;

    public void doDefault() {
        if (Objects.isNull(retailPrice)) {
            retailPrice = BigDecimal.ZERO;
        }

        if (Objects.isNull(stock)) {
            this.stock = 10;
        }

        if (Objects.isNull(discontinued)) {
            this.discontinued = false;
        }
    }
}
