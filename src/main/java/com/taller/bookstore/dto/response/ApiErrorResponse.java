package com.taller.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private String status;
    private int code;
    private String message;
    private List<String> errors;
    private Instant timestamp;
    private String path;
}