package com.example.library.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.model.Book;
import com.example.library.model.entity.BookEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorEntityMapper.class})
public interface BookEntityMapper {

	 BookEntity toEntity(Book model);
	
	 Book toModel(BookEntity entity);
}
