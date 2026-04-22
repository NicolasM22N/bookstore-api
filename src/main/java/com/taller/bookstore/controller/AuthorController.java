package com.taller.bookstore.controller;

import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.entity.Author;
import com.taller.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ApiResponse<Author> create(@RequestBody Author author) {
        return new ApiResponse<>(
                "success",
                201,
                "Author created successfully",
                authorService.create(author),
                Instant.now()
        );
    }

    @GetMapping
    public ApiResponse<List<Author>> findAll() {
        return new ApiResponse<>(
                "success",
                200,
                "Authors retrieved successfully",
                authorService.findAll(),
                Instant.now()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Author> findById(@PathVariable Long id) {
        return new ApiResponse<>(
                "success",
                200,
                "Author found",
                authorService.findById(id),
                Instant.now()
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Author> update(@PathVariable Long id, @RequestBody Author author) {
        return new ApiResponse<>(
                "success",
                200,
                "Author updated successfully",
                authorService.update(id, author),
                Instant.now()
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        authorService.delete(id);

        return new ApiResponse<>(
                "success",
                200,
                "Author deleted successfully",
                null,
                Instant.now()
        );
    }
}