package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.response.OrderItemResponse;
import com.taller.bookstore.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItem item) {
        Long bookId = item.getBook() == null ? null : item.getBook().getId();

        return new OrderItemResponse(
                item.getId(),
                bookId,
                item.getBookTitle(),
                item.getQuantity(),
                item.getPrice(),
                item.getSubtotal()
        );
    }
}
