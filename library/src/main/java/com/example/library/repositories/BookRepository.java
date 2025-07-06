package com.example.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.model.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors WHERE b.isbn = :isbn")
	//Мб потом сделаю EntityGraph
	public Optional<Book> findByIsbn(@Param(value = "isbn") String isbn);
}
