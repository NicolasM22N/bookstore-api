package com.taller.bookstore.service.impl;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.exception.custom.AuthorHasBooksException;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.mapper.AuthorMapper;
import com.taller.bookstore.mapper.BookMapper;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.repository.BookRepository;
import com.taller.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @Override
    public AuthorResponse create(AuthorRequest request) {
        return authorMapper.toResponse(authorRepository.save(authorMapper.toEntity(request)));
    }

    @Override
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    @Override
    public AuthorResponse findById(Long id) {
        return authorMapper.toResponse(getAuthor(id));
    }

    @Override
    public AuthorResponse update(Long id, AuthorRequest request) {
        Author existing = getAuthor(id);
        existing.setName(request.getName());
        existing.setBiography(request.getBiography());
        existing.setEmail(request.getEmail());
        return authorMapper.toResponse(authorRepository.save(existing));
    }

    @Override
    public List<BookResponse> findBooks(Long id) {
        getAuthor(id);
        return bookRepository.findByAuthor_Id(id).stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Author author = getAuthor(id);
        if (bookRepository.existsByAuthor_Id(id)) {
            throw new AuthorHasBooksException("Author with id " + id + " has associated books");
        }
        authorRepository.delete(author);
    }

    private Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }
}
