package com.example.library.controllers.api;

import java.net.URI;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.DTO.BookDTO;
import com.example.library.model.DTO.validation.CreateValidationGroup;
import com.example.library.storages.BookStorage;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/book")
public class BookApiController {
	
	private final BookStorage storage;
	
	public BookApiController(BookStorage storage) {
		this.storage = storage;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return ResponseEntity.ok(storage.getAllBooks());
	}
	
	@GetMapping
	public ResponseEntity<BookDTO> getBookByIsbn(@RequestParam String isbn) {
		return ResponseEntity.of(storage.findByIsbn(isbn));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		return ResponseEntity.of(storage.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
		storage.deleteBookById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping										
	public ResponseEntity<BookDTO> addBook(@RequestBody @Validated(CreateValidationGroup.class) BookDTO dto) {
		BookDTO response = storage.addBook(dto);
		return ResponseEntity.created(URI.create("/api/book/" + String.valueOf(response.id()))).body(dto);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<BookDTO> alterBook(@PathVariable Long id, @RequestBody @Validated BookDTO dto) {
		BookDTO response = storage.alterBook(id, dto);
		return response != null ? ResponseEntity.created(URI.create("/api/book/" + String.valueOf(response.id()))).body(dto) :
			ResponseEntity.badRequest().build();
	}
	
	//------------------------------------------------------------------------------------------------------------------------
	
	@ExceptionHandler({
	    DataAccessException.class,
	    EntityNotFoundException.class 
	})
	public ResponseEntity<String> handleDataBaseConstraintsVioloationExcepttion(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	} 
}
