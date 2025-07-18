package com.example.library.storages;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.library.model.entity.AuthorEntity;
import com.example.library.repositories.AuthorRepository;
import lombok.Value;
import org.springframework.stereotype.Component;

import com.example.library.mappers.AuthorDTOMapper;
import com.example.library.mappers.AuthorEntityMapper;
import com.example.library.mappers.BookDTOMapper;
import com.example.library.mappers.BookEntityMapper;
import com.example.library.model.DTO.BookDTO;
import com.example.library.model.entity.BookEntity;
import com.example.library.repositories.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Value
@Component
public class BookStorage {
	
	BookRepository repository;
	AuthorRepository authorRepository;
	BookEntityMapper bookEntityMapper;
	BookDTOMapper bookDTOMapper;
	AuthorEntityMapper authorEntityMapper;
	AuthorDTOMapper authorDTOMapper;

	public List<BookDTO> getAllBooks() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(bookEntityMapper::toModel)
				.map(bookDTOMapper::toDTO)
				.collect(Collectors.toList());
	}

	public Optional<BookDTO> getBookById(Long id) {
		return repository.findById(id)
				.map(bookEntityMapper::toModel)
				.map(bookDTOMapper::toDTO);
	}

	public Optional<BookDTO> getBookByIsbn(String isbn) {
		return repository.findByIsbn(isbn)
				.map(bookEntityMapper::toModel)
				.map(bookDTOMapper::toDTO);
	}

	public void deleteBookById(Long id) {
		repository.deleteById(id);
	}

	public BookDTO createBook(BookDTO bookDTO) {
		Set<AuthorEntity> authors = bookDTO.authors().stream().map((e) -> {
			return authorRepository.findByName(e.firstName(), e.middleName(), e.lastName())
					.orElse(authorEntityMapper.toEntity(authorDTOMapper.toModel(e)));
		}).collect(Collectors.toSet());

		BookEntity result = bookEntityMapper.toEntity(bookDTOMapper.toModel(bookDTO));
		result.setAuthors(authors);

		return bookDTOMapper.toDTO(bookEntityMapper.toModel(repository.save(result)));
	}

	public BookDTO updateBook(BookDTO bookDTO) {
		BookEntity target = repository.findById(bookDTO.id())
				.orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookDTO.id() + " doesnt exist"));

		Set<AuthorEntity> authors = bookDTO.authors().stream().map((e) -> {
			return authorRepository.findByName(e.firstName(), e.middleName(), e.lastName())
					.orElse(authorEntityMapper.toEntity(authorDTOMapper.toModel(e)));
		}).collect(Collectors.toSet());

		target.setAuthors(authors);
		target.setIsbn(bookDTO.isbn());
		target.setName(bookDTO.name());
		target.setGenre(bookDTO.genre());
		target.setDescription(bookDTO.description());

		return bookDTOMapper.toDTO(bookEntityMapper.toModel(repository.save(target)));
	}
}
