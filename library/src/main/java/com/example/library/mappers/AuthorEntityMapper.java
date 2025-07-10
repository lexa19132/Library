package com.example.library.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.model.Author;
import com.example.library.model.entity.AuthorEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AuthorEntityMapper {
	
	public abstract Author toModel(AuthorEntity entity);
	
	public abstract AuthorEntity toEntity(Author model);
	
	public abstract Set<Author> toModelSet(Set<AuthorEntity> entitySet);
	
	public abstract Set<AuthorEntity> toEntitySet(Set<Author> modelSet);
}
