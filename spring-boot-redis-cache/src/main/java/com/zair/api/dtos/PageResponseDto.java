package com.zair.api.dtos;


import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponseDto<T>(
        List<T> data,
        int page,
        int size,
        int totalPages,
        long totalElements,
        boolean first,
        boolean last
) {
    public static <T> PageResponseDto<T> from(Page<T> page) {
        return new PageResponseDto<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}
