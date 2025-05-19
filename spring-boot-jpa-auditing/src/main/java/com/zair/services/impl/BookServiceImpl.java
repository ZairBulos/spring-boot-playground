package com.zair.services.impl;

import com.zair.api.dtos.request.BookRequestDto;
import com.zair.api.dtos.response.BookResponseDto;
import com.zair.data.entities.BookEntity;
import com.zair.data.repositories.BookRepository;
import com.zair.services.BookService;
import com.zair.services.exceptions.ConflictException;
import com.zair.services.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookResponseDto read(String isbn) {
        BookEntity book = bookRepository
                .findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book: " + isbn));
        return BookResponseDto.toDto(book);
    }

    @Override
    @Transactional
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        assertNotExist(bookRequestDto.getIsbn());

        bookRequestDto.doDefault();
        BookEntity saved = bookRepository.save(bookRequestDto.toEntity());
        return BookResponseDto.toDto(saved);
    }

    @Override
    @Transactional
    public BookResponseDto update(String isbn, BookRequestDto bookRequestDto) {
        BookEntity book = bookRepository
                .findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book: " + isbn));

        bookRequestDto.doDefault();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setDeleted(bookRequestDto.getDeleted());
        bookRepository.save(book);

        return read(isbn);
    }

    @Override
    @Transactional
    public void delete(String isbn) {
        BookEntity book = bookRepository
                .findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book: " + isbn));
        book.setDeleted(true);
        bookRepository.save(book);
    }

    private void assertNotExist(String isbn) {
        if (bookRepository.findByIsbn(isbn).isPresent()) {
            throw new ConflictException("Book already exists: " + isbn);
        }
    }
}
