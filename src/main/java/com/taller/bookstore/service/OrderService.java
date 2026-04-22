package com.taller.bookstore.service;

import com.taller.bookstore.dto.request.OrderRequest;
import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request, String email);

    List<OrderResponse> findAll();

    List<OrderResponse> findByCustomer(String email);

    OrderResponse findById(Long id);

    OrderResponse updateStatus(Long id, OrderStatus status);

    void delete(Long id);
}
