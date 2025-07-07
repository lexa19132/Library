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
import com.example.library.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository repository;
	
	private final BookMapper bookMapper;
	
	private final AuthorMapper authorMapper;
	
//	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
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
		repository.findById(id).ifPresent((e) -> {
			e.setAuthors(authorMapper.toAuthorEntitySet(book.authors()));
			e.setDescription(book.description());
			e.setGenre(book.genre());
			e.setIsbn(book.isbn());
			e.setName(book.name());
			repository.save(e);
		});
		return  bookMapper.toDTO(repository.findById(id).get());
	}
}
