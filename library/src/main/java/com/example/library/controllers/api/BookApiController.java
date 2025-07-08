package com.example.library.controllers.api;

import java.net.URI;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.NoIdBookDTO;
import com.example.library.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookApiController {
	
	private final BookService service;
	
	public BookApiController(BookService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<BookDTO> getBookByIsbn(@RequestParam String isbn) {
		return ResponseEntity.of(service.getBookByIsbn(isbn));
	}
	
	@GetMapping("/all")
	public List<BookDTO> getAllBooks() {
		return service.getAllBooks();
		//Тут в целом можно не оборачивать в responseEntity, так как я всегда возвращать 200 планировал.
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		return ResponseEntity.of(service.getBookById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
		service.deleteBookById(id);
		return ResponseEntity.noContent().build();
		//Как будто бы в целом неплохо.
	}	
	
	@PostMapping
	public ResponseEntity<BookDTO> addBook(@RequestBody(required = true) @Valid NoIdBookDTO book) {
		BookDTO dto = service.addBook(book);
		return ResponseEntity.created(URI.create("/api/book/" + String.valueOf(dto.id()))).body(dto);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<BookDTO> alterBook(@PathVariable Long id, @RequestBody(required = true) @Valid NoIdBookDTO book) {
		BookDTO dto = service.alterBook(id, book);
		return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.created(URI.create("/api/book/" + String.valueOf(dto.id()))).body(dto);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------
	//Вообще говорят, что есть способ получше обрабатывать исключения в спринге, но мне подходит и этот так как у меня всего 1 контроллер.
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<String> handleDataBaseConstraintsVioloationExcepttion(DataAccessException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
