package com.zair.api.controllers;

import com.zair.api.dtos.request.BookRequestDto;
import com.zair.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BookController.BOOKS)
@RequiredArgsConstructor
public class BookController {
    public static final String BOOKS = "/books";
    public static final String ID_ISBN = "/{isbn}";

    private final BookService bookService;

    @GetMapping(ID_ISBN)
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<?> read(@PathVariable String isbn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.read(isbn));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<?> create(@RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.create(bookRequestDto));
    }

    @PutMapping(ID_ISBN)
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    public ResponseEntity<?> update(@PathVariable String isbn, @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.update(isbn, bookRequestDto));
    }

    @DeleteMapping(ID_ISBN)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable String isbn) {
        bookService.delete(isbn);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
