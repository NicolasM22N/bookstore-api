package com.taller.bookstore.service;

import com.taller.bookstore.entity.Order;
import com.taller.bookstore.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> findAll();

    List<Order> findByCustomer(String email);

    Order findById(Long id);

    Order updateStatus(Long id, OrderStatus status);

    void delete(Long id);
}