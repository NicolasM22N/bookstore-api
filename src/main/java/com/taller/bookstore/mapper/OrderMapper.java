package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerEmail(),
                order.getTotal(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getItems().stream()
                        .map(orderItemMapper::toResponse)
                        .toList()
        );
    }
}
