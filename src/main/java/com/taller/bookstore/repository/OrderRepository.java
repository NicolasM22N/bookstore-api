package com.taller.bookstore.repository;

import com.taller.bookstore.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user", "items", "items.book"})
    List<Order> findAll();

    @EntityGraph(attributePaths = {"user", "items", "items.book"})
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = {"user", "items", "items.book"})
    List<Order> findByUser_EmailIgnoreCase(String email);
}
