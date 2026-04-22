package com.taller.bookstore.service;

import com.taller.bookstore.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> findAll();

    Order findById(Long id);

    void delete(Long id);
}