package com.zair.services;

import com.zair.api.dtos.request.BookRequestDto;
import com.zair.api.dtos.response.BookResponseDto;

public interface BookService {
    BookResponseDto read(String isbn);
    BookResponseDto create(BookRequestDto bookRequestDto);
    BookResponseDto update(String isbn, BookRequestDto bookRequestDto);
    void delete(String isbn);
}
