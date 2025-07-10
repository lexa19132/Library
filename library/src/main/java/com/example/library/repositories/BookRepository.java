package com.example.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.model.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>{
	
	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors WHERE b.isbn = :isbn")
	public Optional<BookEntity> findByIsbn(
		@Param(value = "isbn") String isbn
	);
}
