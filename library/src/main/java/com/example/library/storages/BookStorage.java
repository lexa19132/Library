package com.example.library.storages;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.example.library.mappers.AuthorDTOMapper;
import com.example.library.mappers.AuthorEntityMapper;
import com.example.library.mappers.BookDTOMapper;
import com.example.library.mappers.BookEntityMapper;
import com.example.library.model.DTO.BookDTO;
import com.example.library.model.entity.BookEntity;
import com.example.library.repositories.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component
public class BookStorage {
	
	private final BookRepository repository;
	
	private final BookEntityMapper bookEntityMapper;
	
	private final BookDTOMapper bookDTOMapper;
	
	private final AuthorEntityMapper authorEntityMapper;
	
	private final AuthorDTOMapper authorDTOMapper;
	
	//4 маппера, кста.
	
	public BookStorage(BookRepository repository, BookEntityMapper bookEntityMapper, BookDTOMapper bookDTOMapper, AuthorEntityMapper authorEntityMapper, AuthorDTOMapper authorDTOMapper) {
		this.repository = repository;
		this.bookEntityMapper = bookEntityMapper;
		this.bookDTOMapper = bookDTOMapper;
		this.authorEntityMapper = authorEntityMapper;
		this.authorDTOMapper = authorDTOMapper;
	}
	
	public List<BookDTO> getAllBooks() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
			.map((e) -> bookEntityMapper.toModel(e))
			.map((e) -> bookDTOMapper.toDTO(e))
			.collect(Collectors.toList());
	}
	
	public Optional<BookDTO> findByIsbn(String isbn) {
		return repository.findByIsbn(isbn).map((e) -> bookEntityMapper.toModel(e)).map((e) -> bookDTOMapper.toDTO(e));
	}
	
	public Optional<BookDTO> findById(Long id) {
		return repository.findById(id).map((e) -> bookEntityMapper.toModel(e)).map((e) -> bookDTOMapper.toDTO(e));
	}
	
	public void deleteBookById(Long id) {
		repository.findById(id).ifPresentOrElse(
			(e) -> repository.delete(e), 
			() -> {
				throw new EntityNotFoundException("Could not delete book by id, such book doesnt exists.");
			}
		);
	}
	
	public BookDTO addBook(BookDTO dto) { 
		return bookDTOMapper.toDTO(bookEntityMapper.toModel(repository.save(bookEntityMapper.toEntity(bookDTOMapper.toModel(dto)))));
	}
	
	@Transactional //Как в hibernate работает?
	public BookDTO alterBook(Long id, BookDTO dto) {	
		BookEntity book = repository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
		
		book.setAuthors(authorEntityMapper.toEntitySet(authorDTOMapper.toModelSet(dto.authors())));
		book.setDescription(dto.description());
		book.setGenre(dto.genre());
		book.setIsbn(dto.isbn());
		book.setName(dto.name());
		
		return bookDTOMapper.toDTO(bookEntityMapper.toModel(book));
		
	}
}
