package com.example.library.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.model.Author;
import com.example.library.model.DTO.AuthorDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorDTOMapper {
	
	Author toModel(AuthorDTO dto);
	
	AuthorDTO toDTO(Author model);

	Set<Author> toModelSet(Set<AuthorDTO> dtoSet);
	
	Set<AuthorDTO> toDtoSet(Set<Author> modelSet);
}
