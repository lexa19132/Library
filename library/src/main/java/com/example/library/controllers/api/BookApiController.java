package com.example.library.controllers.api;

import java.net.URI;
import java.util.List;

import com.example.library.model.DTO.validation.UpdateValidationGroup;
import com.example.library.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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



@RestController
@RequestMapping("/api/book")
public class BookApiController {

	private final BookStorage storage;

	private static final Logger LOGGER = LoggerFactory.getLogger(BookApiController.class);

	public BookApiController(BookStorage storage) {
		this.storage = storage;
	}

	@GetMapping("/all")
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return ResponseEntity.ok().body(storage.getAllBooks());
	}

	@GetMapping("/{id}")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		return ResponseEntity.of(storage.getBookById(id));
	}

	@GetMapping()
	public ResponseEntity<BookDTO> getBookByIsbn(@RequestParam String isbn) {
		return ResponseEntity.of(storage.getBookByIsbn(isbn));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
		storage.deleteBookById(id);
		return ResponseEntity.accepted().build();
	}

	@PostMapping
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<BookDTO> createBook(@RequestBody @Validated(CreateValidationGroup.class) BookDTO bookDTO) {
		BookDTO newBookDTO = storage.createBook(bookDTO);
		return ResponseEntity.created(URI.create("/api/book" + String.valueOf(newBookDTO.id()))).body(newBookDTO);
	}

	@PatchMapping
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<BookDTO> updateBook(@RequestBody @Validated(UpdateValidationGroup.class) BookDTO bookDTO) {
		BookDTO newBookDTO = storage.updateBook(bookDTO);
		return ResponseEntity.created(URI.create("/api/book" + String.valueOf(newBookDTO.id()))).body(newBookDTO);
	}
}
