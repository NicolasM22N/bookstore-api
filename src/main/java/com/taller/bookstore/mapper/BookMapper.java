package com.taller.bookstore.mapper;

import com.taller.bookstore.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(Book book) {
        return book;
    }

    public Book toResponse(Book book) {
        return book;
    }
}