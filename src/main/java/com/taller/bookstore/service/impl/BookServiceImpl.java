package com.taller.bookstore.service.impl;

import com.taller.bookstore.entity.Book;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.repository.BookRepository;
import com.taller.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll(
            String author,
            String category,
            int page,
            int size) {

        if (author != null && !author.isBlank()) {
            return bookRepository
                    .findByAuthor_NameContainingIgnoreCase(author);
        }

        if (category != null && !category.isBlank()) {
            return bookRepository
                    .findByCategories_NameContainingIgnoreCase(category);
        }

        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book with id " + id + " not found"));
    }

    @Override
    public Book update(Long id, Book book) {

        Book existing = findById(id);

        existing.setTitle(book.getTitle());
        existing.setIsbn(book.getIsbn());
        existing.setPrice(book.getPrice());
        existing.setStock(book.getStock());

        return bookRepository.save(existing);
    }

    @Override
    public void delete(Long id) {

        Book existing = findById(id);

        bookRepository.delete(existing);
    }
}