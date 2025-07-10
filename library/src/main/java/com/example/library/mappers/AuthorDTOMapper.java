package com.example.library.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.model.Author;
import com.example.library.model.DTO.AuthorDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AuthorDTOMapper {
	
	public abstract Author toModel(AuthorDTO dto);
	
	public abstract AuthorDTO toDTO(Author model);
	
	public abstract Set<Author> toModelSet(Set<AuthorDTO> dtoSet);
	
	public abstract Set<AuthorDTO> toDtoSet(Set<Author> modelSet);

}
