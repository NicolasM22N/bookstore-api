package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.OrderRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.entity.OrderStatus;
import com.taller.bookstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @Valid @RequestBody OrderRequest request,
            Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "success",
                201,
                "Order created successfully",
                orderService.create(request, authentication.getName()),
                Instant.now()
        ));
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> findAll() {
        return new ApiResponse<>("success", 200, "Orders retrieved successfully", orderService.findAll(), Instant.now());
    }

    @GetMapping("/my")
    public ApiResponse<List<OrderResponse>> myOrders(Authentication authentication) {
        return new ApiResponse<>("success", 200, "My orders retrieved successfully", orderService.findByCustomer(authentication.getName()), Instant.now());
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> findById(@PathVariable Long id) {
        return new ApiResponse<>("success", 200, "Order found", orderService.findById(id), Instant.now());
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<OrderResponse> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return new ApiResponse<>("success", 200, "Order status updated", orderService.updateStatus(id, status), Instant.now());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new ApiResponse<>("success", 200, "Order deleted successfully", null, Instant.now());
    }
}
