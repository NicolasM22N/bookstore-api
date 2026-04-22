package com.taller.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponse {

    private Long id;
    private String name;
    private String biography;
    private String email;
}
