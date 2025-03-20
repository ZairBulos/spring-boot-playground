package com.zair.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class CartResponseDTO {
    private String message;
    private List<String> items;
}
