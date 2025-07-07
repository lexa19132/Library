package com.example.library.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.NoIdBookDTO;
import com.example.library.model.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorMapper.class})
public interface BookMapper {
	
	BookDTO toDTO(Book book);
	
	Book toEntity(BookDTO dto);
	
	NoIdBookDTO toNoIdBookDTO(Book book);
	
	@Mapping(target = "id", ignore = true)
	Book toEntity(NoIdBookDTO dto);
}
