package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.BookRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> create(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "success",
                201,
                "Book created successfully",
                bookService.create(request),
                Instant.now()
        ));
    }

    @GetMapping
    public ApiResponse<List<BookResponse>> findAll(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new ApiResponse<>(
                "success",
                200,
                "Books retrieved successfully",
                bookService.findAll(author, category, page, size),
                Instant.now()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<BookResponse> findById(@PathVariable Long id) {
        return new ApiResponse<>("success", 200, "Book found", bookService.findById(id), Instant.now());
    }

    @PutMapping("/{id}")
    public ApiResponse<BookResponse> update(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return new ApiResponse<>("success", 200, "Book updated successfully", bookService.update(id, request), Instant.now());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return new ApiResponse<>("success", 200, "Book deleted successfully", null, Instant.now());
    }
}
