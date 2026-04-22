package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.CategoryRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.BookResponse;
import com.taller.bookstore.dto.response.CategoryResponse;
import com.taller.bookstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "success",
                201,
                "Category created successfully",
                categoryService.create(request),
                Instant.now()
        ));
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> findAll() {
        return new ApiResponse<>("success", 200, "Categories retrieved successfully", categoryService.findAll(), Instant.now());
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> findById(@PathVariable Long id) {
        return new ApiResponse<>("success", 200, "Category found", categoryService.findById(id), Instant.now());
    }

    @GetMapping("/{id}/books")
    public ApiResponse<List<BookResponse>> findBooks(@PathVariable Long id) {
        return new ApiResponse<>("success", 200, "Category books retrieved successfully", categoryService.findBooks(id), Instant.now());
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return new ApiResponse<>("success", 200, "Category updated successfully", categoryService.update(id, request), Instant.now());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ApiResponse<>("success", 200, "Category deleted successfully", null, Instant.now());
    }
}
