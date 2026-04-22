package com.taller.bookstore.service.impl;

import com.taller.bookstore.entity.Author;
import com.taller.bookstore.repository.AuthorRepository;
import com.taller.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author update(Long id, Author author) {

        Author existing = authorRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(author.getName());
        existing.setBiography(author.getBiography());
        existing.setEmail(author.getEmail());

        return authorRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}