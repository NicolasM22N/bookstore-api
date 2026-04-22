package com.taller.bookstore.mapper;

import com.taller.bookstore.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toEntity(OrderItem item) {
        return item;
    }

    public OrderItem toResponse(OrderItem item) {
        return item;
    }
}