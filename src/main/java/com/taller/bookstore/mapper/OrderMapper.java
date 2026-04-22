package com.taller.bookstore.mapper;

import com.taller.bookstore.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(Order order) {
        return order;
    }

    public Order toResponse(Order order) {
        return order;
    }
}