package com.taller.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemResponse {

    private Long id;
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private Double price;
    private Double subtotal;
}
