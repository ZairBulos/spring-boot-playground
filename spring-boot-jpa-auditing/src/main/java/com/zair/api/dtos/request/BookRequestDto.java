package com.zair.api.dtos.request;

import com.zair.data.entities.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {
    private String isbn;
    private String title;
    private String author;
    private Boolean deleted;

    public void doDefault() {
        if (Objects.isNull(deleted)) {
            deleted = false;
        }
    }

    public BookEntity toEntity() {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(this, bookEntity);
        return bookEntity;
    }
}
