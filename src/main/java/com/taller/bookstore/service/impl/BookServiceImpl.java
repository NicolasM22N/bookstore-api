package com.taller.bookstore.service.impl;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.entity.Book;
import com.taller.bookstore.entity.Category;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.mapper.BookMapper;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.repository.BookRepository;
import com.taller.bookstore.repository.CategoryRepository;
import com.taller.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponse create(BookRequest request) {
        Author author = getAuthor(request.getAuthorId());
        Set<Category> categories = getCategories(request.getCategoryIds());
        Book book = bookMapper.toEntity(request, author, categories);
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> findAll(String author, String category, int page, int size) {
        List<Book> books;

        if (author != null && !author.isBlank()) {
            books = bookRepository.findByAuthor_NameContainingIgnoreCase(author);
        } else if (category != null && !category.isBlank()) {
            books = bookRepository.findByCategories_NameContainingIgnoreCase(category);
        } else {
            List<Long> ids = bookRepository.findAll(PageRequest.of(Math.max(page, 0), Math.max(size, 1)))
                    .map(Book::getId)
                    .getContent();
            books = ids.isEmpty() ? List.of() : bookRepository.findAllWithRelationsByIdIn(ids);
        }

        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    public BookResponse findById(Long id) {
        return bookMapper.toResponse(getBook(id));
    }

    @Override
    public BookResponse update(Long id, BookRequest request) {
        Book existing = getBook(id);
        existing.setTitle(request.getTitle());
        existing.setIsbn(request.getIsbn());
        existing.setPrice(request.getPrice());
        existing.setStock(request.getStock());
        existing.setAuthor(getAuthor(request.getAuthorId()));
        existing.setCategories(getCategories(request.getCategoryIds()));
        return bookMapper.toResponse(bookRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(getBook(id));
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    private Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }

    private Set<Category> getCategories(Set<Long> ids) {
        Set<Category> categories = new HashSet<>();
        for (Long id : ids) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
            categories.add(category);
        }
        return categories;
    }
}
