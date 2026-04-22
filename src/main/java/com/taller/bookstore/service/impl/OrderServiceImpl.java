package com.taller.bookstore.service.impl;

import com.taller.bookstore.dto.request.OrderItemRequest;
import com.taller.bookstore.dto.request.OrderRequest;
import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.entity.Book;
import com.taller.bookstore.entity.Order;
import com.taller.bookstore.entity.OrderItem;
import com.taller.bookstore.entity.OrderStatus;
import com.taller.bookstore.entity.User;
import com.taller.bookstore.exception.custom.InsufficientStockException;
import com.taller.bookstore.exception.custom.InvalidOrderStateException;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.mapper.OrderMapper;
import com.taller.bookstore.repository.BookRepository;
import com.taller.bookstore.repository.OrderRepository;
import com.taller.bookstore.repository.UserRepository;
import com.taller.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponse create(OrderRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        Order order = new Order();
        order.setUser(user);
        order.setCustomerEmail(user.getEmail());
        order.setCreatedAt(Instant.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> items = new ArrayList<>();
        double total = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Book book = bookRepository.findById(itemRequest.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book with id " + itemRequest.getBookId() + " not found"));

            if (book.getStock() < itemRequest.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for book with id " + book.getId());
            }

            double subtotal = book.getPrice() * itemRequest.getQuantity();
            OrderItem item = new OrderItem();
            item.setBook(book);
            item.setBookTitle(book.getTitle());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(book.getPrice());
            item.setSubtotal(subtotal);
            items.add(item);
            total += subtotal;
        }

        order.setItems(items);
        order.setTotal(total);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> findByCustomer(String email) {
        return orderRepository.findByUser_EmailIgnoreCase(email).stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse findById(Long id) {
        return orderMapper.toResponse(getOrder(id));
    }

    @Override
    @Transactional
    public OrderResponse updateStatus(Long id, OrderStatus status) {
        Order order = getOrder(id);

        if (order.getStatus() == OrderStatus.CONFIRMED && status == OrderStatus.CANCELLED) {
            throw new InvalidOrderStateException("Cannot cancel a confirmed order");
        }

        if (status == OrderStatus.CONFIRMED && order.getStatus() != OrderStatus.CONFIRMED) {
            for (OrderItem item : order.getItems()) {
                Book book = item.getBook();
                if (book.getStock() < item.getQuantity()) {
                    throw new InsufficientStockException("Insufficient stock for book with id " + book.getId());
                }
                book.setStock(book.getStock() - item.getQuantity());
            }
        }

        order.setStatus(status);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(getOrder(id));
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id " + id + " not found"));
    }
}
