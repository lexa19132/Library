package com.example.library.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.model.Author;
import com.example.library.model.entity.AuthorEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorEntityMapper {
	
	Author toModel(AuthorEntity entity);
	
	AuthorEntity toEntity(Author model);
	
	Set<Author> toModelSet(Set<AuthorEntity> entitySet);
	
	Set<AuthorEntity> toEntitySet(Set<Author> modelSet);
}
