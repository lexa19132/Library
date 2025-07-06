package com.example.library.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.DescribedBookDTO;
import com.example.library.services.BookService;

@RestController
@RequestMapping("/api/book")
public class BookApiController {
	
	private final BookService service;
	
	public BookApiController(BookService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<DescribedBookDTO> getBookByIsbn(@RequestParam String isbn) {
		return ResponseEntity.of(service.getBookByIsbn(isbn));
	}
	
	@GetMapping("/all")
	public List<BookDTO> getAllBooks() {
		return service.getAllBooks();
		//Тут в целом можно не оборачивать в responseEntity, так как я всегда возвращать 200 планировал.
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DescribedBookDTO> getBookById(@PathVariable Long id) {
		return ResponseEntity.of(service.getBookById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
		service.deleteBookById(id);
		return ResponseEntity.noContent().build();
		//Как будто бы в целом неплохо.
	}	
}
