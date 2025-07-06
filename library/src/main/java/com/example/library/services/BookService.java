package com.example.library.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.DescribedBookDTO;
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
		//Пока не знаю, хочу ли я вернуть true/false тут.
	}
}
