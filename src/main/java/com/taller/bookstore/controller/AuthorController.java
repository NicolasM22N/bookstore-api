package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.AuthorRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.AuthorResponse;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponse>> create(@Valid @RequestBody AuthorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "success",
                201,
                "Author created successfully",
                authorService.create(request),
                Instant.now()
        ));
    }

    @GetMapping
    public ApiResponse<List<AuthorResponse>> findAll() {
        return new ApiResponse<>("success", 200, "Authors retrieved successfully", authorService.findAll(), Instant.now());
    }

    @GetMapping("/{id}")
    public ApiResponse<AuthorResponse> findById(@PathVariable Long id) {
        return new ApiResponse<>("success", 200, "Author found", authorService.findById(id), Instant.now());
    }

    @GetMapping("/{id}/books")
    public ApiResponse<List<BookResponse>> findBooks(@PathVariable Long id) {
        return new ApiResponse<>("success", 200, "Author books retrieved successfully", authorService.findBooks(id), Instant.now());
    }

    @PutMapping("/{id}")
    public ApiResponse<AuthorResponse> update(@PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
        return new ApiResponse<>("success", 200, "Author updated successfully", authorService.update(id, request), Instant.now());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        authorService.delete(id);
        return new ApiResponse<>("success", 200, "Author deleted successfully", null, Instant.now());
    }
}
