package com.taller.bookstore.service;

import com.taller.bookstore.entity.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    List<Book> findAll(String author, String category, int page, int size);

    Book findById(Long id);

    Book update(Long id, Book book);

    void delete(Long id);
}