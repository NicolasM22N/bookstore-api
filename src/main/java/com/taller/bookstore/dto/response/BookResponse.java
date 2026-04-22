package com.taller.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String isbn;
    private Double price;
    private Integer stock;
    private Long authorId;
    private String authorName;
    private Set<CategoryResponse> categories;
}
