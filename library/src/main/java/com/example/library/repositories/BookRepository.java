package com.example.library.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.library.model.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b WHERE isbn = ?1")
	public Book findByIsbn(String isbn);
}
