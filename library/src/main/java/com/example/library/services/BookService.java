package com.example.library.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.NoIdBookDTO;
import com.example.library.mappers.AuthorMapper;
import com.example.library.mappers.BookMapper;
import com.example.library.model.entity.Book;
import com.example.library.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository repository;
	
	private final BookMapper bookMapper;
	
	private final AuthorMapper authorMapper;
	
	public BookService(BookRepository repository, BookMapper bookMapper, AuthorMapper authorMapper ) {
		this.repository = repository;
		this.bookMapper = bookMapper;
		this.authorMapper = authorMapper;
	}
	
	public Optional<BookDTO> getBookByIsbn(String isbn) {
		return repository.findByIsbn(isbn).map((e) -> bookMapper.toDTO(e));
	}
	
	public List<BookDTO> getAllBooks() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).map((e) -> bookMapper.toDTO(e)).collect(Collectors.toList());
	}
	
	public Optional<BookDTO> getBookById(Long id) {
		return repository.findById(id).map((e) ->  bookMapper.toDTO(e));
	}	
	
	public void deleteBookById(Long id) {
		repository.findById(id).ifPresent((e) -> repository.delete(e));
	}
	
	public BookDTO addBook(NoIdBookDTO book) {
		return  bookMapper.toDTO(repository.save(bookMapper.toEntity(book)));   
	}
	
	public BookDTO alterBook(Long id, NoIdBookDTO book) {
		Optional<Book> queryResult = repository.findById(id);
		if(queryResult.isPresent()) {
			Book target = queryResult.get();
			target.setAuthors(authorMapper.toAuthorEntitySet(book.authors()));
			target.setDescription(book.description());
			target.setGenre(book.genre());
			target.setIsbn(book.isbn());
			target.setName(book.name());
			repository.save(target);
			return bookMapper.toDTO(target);
		}
		return null;
	}
}
