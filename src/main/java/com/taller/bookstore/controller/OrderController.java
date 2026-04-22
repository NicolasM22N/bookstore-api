package com.taller.bookstore.controller;

import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.entity.Order;
import com.taller.bookstore.entity.OrderStatus;
import com.taller.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<Order> create(@RequestBody Order order) {
        return new ApiResponse<>(
                "success",
                201,
                "Order created successfully",
                orderService.create(order),
                Instant.now()
        );
    }

    @GetMapping
    public ApiResponse<List<Order>> findAll() {
        return new ApiResponse<>(
                "success",
                200,
                "Orders retrieved successfully",
                orderService.findAll(),
                Instant.now()
        );
    }

    @GetMapping("/my")
    public ApiResponse<List<Order>> myOrders(
            @RequestParam String email) {

        return new ApiResponse<>(
                "success",
                200,
                "My orders retrieved successfully",
                orderService.findByCustomer(email),
                Instant.now()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Order> findById(@PathVariable Long id) {
        return new ApiResponse<>(
                "success",
                200,
                "Order found",
                orderService.findById(id),
                Instant.now()
        );
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return new ApiResponse<>(
                "success",
                200,
                "Order status updated",
                orderService.updateStatus(id, status),
                Instant.now()
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        orderService.delete(id);

        return new ApiResponse<>(
                "success",
                200,
                "Order deleted successfully",
                null,
                Instant.now()
        );
    }
}