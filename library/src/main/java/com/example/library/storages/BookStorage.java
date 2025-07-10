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
import com.example.library.model.Book;
import com.example.library.model.DTO.BookDTO;
import com.example.library.repositories.BookRepository;

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
		repository.deleteById(id);
	}
	
	public BookDTO addBook(BookDTO dto) { //
		return bookDTOMapper.toDTO(bookEntityMapper.toModel(repository.save(bookEntityMapper.toEntity(bookDTOMapper.toModel(dto)))));
		//кошмар
	}
	
	public BookDTO alterBook(Long id, BookDTO dto) {
		if(dto.id() != null) {
			return bookDTOMapper.toDTO(bookEntityMapper.toModel(repository.save(bookEntityMapper.toEntity(bookDTOMapper.toModel(dto)))));
			//да, этого достаточно.
		}
		
		var queryResult = repository.findById(id);
		if (queryResult.isPresent()) {
			Book model = bookEntityMapper.toModel(queryResult.get());
			return queryResult.map((e) -> {
				e.setName(model.getName());
				e.setGenre(model.getGenre());
				e.setDescription(model.getDescription());
				e.setIsbn(model.getIsbn());
				e.setAuthors(authorEntityMapper.toEntitySet(model.getAuthors()));
				return bookDTOMapper.toDTO(bookEntityMapper.toModel(e));
			}).get();
		}
		return null;
	}
}
