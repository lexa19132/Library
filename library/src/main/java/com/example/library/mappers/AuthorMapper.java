package com.example.library.mappers;

import java.util.Optional;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.library.DTO.AuthorDTO;
import com.example.library.model.entity.Author;
import com.example.library.repositories.AuthorRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AuthorMapper {
	
	@Autowired
	public AuthorRepository repository;

	public Author toEntity(AuthorDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Optional<Author> queryResult = repository.findByFullName(dto.firstName(), dto.middleName(), dto.lastName());
		if(queryResult.isPresent()) {
			return queryResult.get();
		} else {
			return new Author(null, dto.firstName(), dto.middleName(), dto.lastName());
		}
		
	}
	
	public abstract AuthorDTO toDTO(Author entity);
	
	public abstract Set<AuthorDTO> toAuthorDTOSet(Set<Author> authors);
	
	public abstract Set<Author> toAuthorEntitySet(Set<AuthorDTO> authorDTOs);
}
