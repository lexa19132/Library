package com.example.library.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.DescribedBookDTO;
import com.example.library.DTO.NoIdBookDTO;
import com.example.library.mappers.BookMapper;
import com.example.library.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository repository;
	
	private final BookMapper mapper;
	
	public BookService(BookRepository repository, BookMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public Optional<DescribedBookDTO> getBookByIsbn(String isbn) {
		return repository.findByIsbn(isbn).map((e) -> mapper.toDescribedDTO(e));
	}
	
	public List<BookDTO> getAllBooks() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).map((e) -> mapper.toDTO(e)).collect(Collectors.toList());
	}
	
	public Optional<DescribedBookDTO> getBookById(Long id) {
		return repository.findById(id).map((e) -> mapper.toDescribedDTO(e));
	}	
	
	public void deleteBookById(Long id) {
		repository.findById(id).ifPresent((e) -> repository.delete(e));
	}
	
	public DescribedBookDTO addBook(NoIdBookDTO book) {
		return mapper.toDescribedDTO(repository.save(mapper.toEntity(book)));   
	}
	
	public DescribedBookDTO alterBook(Long id, NoIdBookDTO book) {
		repository.findById(id).ifPresent((e) -> {
			e.setAuthors(book.authors());
			e.setDescription(book.description());
			e.setGenre(book.genre());
			e.setIsbn(book.isbn());
			e.setName(book.name());
			repository.save(e);
		});
		return mapper.toDescribedDTO(repository.findById(id).get());
	}
	//Надо будет подумать, что случится если нарушатся constraints в базе, просто тут не написано прокинет ли метод save exception.
}
