package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.dto.response.CategoryResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.entity.Book;
import com.taller.bookstore.entity.Category;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request, Author author, Set<Category> categories) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStock(request.getStock());
        book.setAuthor(author);
        book.setCategories(categories);
        return book;
    }

    public BookResponse toResponse(Book book) {
        Long authorId = book.getAuthor() == null ? null : book.getAuthor().getId();
        String authorName = book.getAuthor() == null ? null : book.getAuthor().getName();
        Set<CategoryResponse> categories = book.getCategories().stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getDescription()
                ))
                .collect(Collectors.toSet());

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPrice(),
                book.getStock(),
                authorId,
                authorName,
                categories
        );
    }
}
