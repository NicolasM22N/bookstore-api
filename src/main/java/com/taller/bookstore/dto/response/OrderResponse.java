package com.taller.bookstore.dto.response;

import com.taller.bookstore.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String customerEmail;
    private Double total;
    private Instant createdAt;
    private OrderStatus status;
    private List<OrderItemResponse> items;
}
