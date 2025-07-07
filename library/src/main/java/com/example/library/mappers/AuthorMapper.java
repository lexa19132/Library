package com.example.library.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.DTO.AuthorDTO;
import com.example.library.model.entity.Author;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {

	Author toEntity(AuthorDTO dto);
	
	AuthorDTO toDTO(Author entity);
	
	Set<AuthorDTO> toAuthorDTOSet(Set<Author> authors);
	
	Set<Author> toAuthorEntitySet(Set<AuthorDTO> authorDTOs);
}
