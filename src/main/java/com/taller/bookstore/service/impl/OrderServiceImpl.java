package com.taller.bookstore.service.impl;

import com.taller.bookstore.entity.Order;
import com.taller.bookstore.repository.OrderRepository;
import com.taller.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        order.setCreatedAt(Instant.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}