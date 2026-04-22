package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse create(BookRequest request);

    List<BookResponse> findAll(String author, String category, int page, int size);

    BookResponse findById(Long id);

    BookResponse update(Long id, BookRequest request);

    void delete(Long id);
}
