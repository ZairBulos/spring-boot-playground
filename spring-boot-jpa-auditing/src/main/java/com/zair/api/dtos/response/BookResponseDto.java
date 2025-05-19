package com.zair.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zair.data.entities.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDto {
    private String isbn;
    private String title;
    private String author;
    private Boolean deleted;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String modifiedBy;

    public static BookResponseDto toDto(BookEntity book) {
        BookResponseDto dto = new BookResponseDto();
        BeanUtils.copyProperties(book, dto);
        return dto;
    }
}
