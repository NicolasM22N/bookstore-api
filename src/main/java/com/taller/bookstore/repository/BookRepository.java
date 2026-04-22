package com.taller.bookstore.repository;

import com.taller.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor_NameContainingIgnoreCase(String author);

    List<Book> findByCategories_NameContainingIgnoreCase(String category);
}