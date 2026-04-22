package com.taller.bookstore.service.impl;

import com.taller.bookstore.entity.Book;
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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book update(Long id, Book book) {

        Book existing = bookRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setTitle(book.getTitle());
        existing.setIsbn(book.getIsbn());
        existing.setPrice(book.getPrice());
        existing.setStock(book.getStock());

        return bookRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}