package com.taller.bookstore.controller;

import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.entity.Category;
import com.taller.bookstore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<Category> create(@RequestBody Category category) {
        return new ApiResponse<>(
                "success",
                201,
                "Category created successfully",
                categoryService.create(category),
                Instant.now()
        );
    }

    @GetMapping
    public ApiResponse<List<Category>> findAll() {
        return new ApiResponse<>(
                "success",
                200,
                "Categories retrieved successfully",
                categoryService.findAll(),
                Instant.now()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Category> findById(@PathVariable Long id) {
        return new ApiResponse<>(
                "success",
                200,
                "Category found",
                categoryService.findById(id),
                Instant.now()
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Category> update(@PathVariable Long id,
                                        @RequestBody Category category) {
        return new ApiResponse<>(
                "success",
                200,
                "Category updated successfully",
                categoryService.update(id, category),
                Instant.now()
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        categoryService.delete(id);

        return new ApiResponse<>(
                "success",
                200,
                "Category deleted successfully",
                null,
                Instant.now()
        );
    }
}