package com.taller.bookstore.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookRequest {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Long authorId;

    private Set<Long> categoryIds = new HashSet<>();
}
