package com.example.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.model.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>{
    //Я не нашел как глобально поставить EntityGraph, поэтому пришлось переопределять методы.
    @Override
    @Query("SELECT be FROM Book be WHERE be.id = :id")
    @EntityGraph(value = "books_authors_entity_graph")
    Optional<BookEntity> findById(@Param(value="id") Long id);

    @Override
    @Query("SELECT be FROM Book be")
    @EntityGraph(value = "books_authors_entity_graph")
    Iterable<BookEntity> findAll();

    @Query("SELECT be FROM Book be WHERE be.isbn = :isbn")
    @EntityGraph(value = "books_authors_entity_graph")
    Optional<BookEntity> findByIsbn(@Param(value="isbn") String isbn);
}
