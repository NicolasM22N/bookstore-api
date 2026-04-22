package com.taller.bookstore.service.impl;

import com.taller.bookstore.entity.Order;
import com.taller.bookstore.entity.OrderStatus;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
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
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByCustomer(String email) {

        return orderRepository.findAll()
                .stream()
                .filter(order ->
                        order.getCustomerEmail().equalsIgnoreCase(email))
                .toList();
    }

    @Override
    public Order findById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order with id " + id + " not found"));
    }

    @Override
    public Order updateStatus(Long id, OrderStatus status) {

        Order order = findById(id);

        order.setStatus(status);

        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {

        Order order = findById(id);

        orderRepository.delete(order);
    }
}