package com.taller.bookstore.repository;

import com.taller.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"author", "categories"})
    Optional<Book> findById(Long id);

    @EntityGraph(attributePaths = {"author", "categories"})
    List<Book> findByAuthor_NameContainingIgnoreCase(String author);

    @EntityGraph(attributePaths = {"author", "categories"})
    List<Book> findByCategories_NameContainingIgnoreCase(String category);

    @EntityGraph(attributePaths = {"author", "categories"})
    List<Book> findByAuthor_Id(Long authorId);

    @EntityGraph(attributePaths = {"author", "categories"})
    List<Book> findByCategories_Id(Long categoryId);

    @Query("select distinct b from Book b left join fetch b.author left join fetch b.categories where b.id in :ids")
    List<Book> findAllWithRelationsByIdIn(Collection<Long> ids);

    boolean existsByAuthor_Id(Long authorId);
}
