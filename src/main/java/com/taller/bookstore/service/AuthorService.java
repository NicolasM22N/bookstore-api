package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.dto.response.BookResponse;

import java.util.List;

public interface AuthorService {

    AuthorResponse create(AuthorRequest request);

    List<AuthorResponse> findAll();

    AuthorResponse findById(Long id);

    AuthorResponse update(Long id, AuthorRequest request);

    List<BookResponse> findBooks(Long id);

    void delete(Long id);
}
