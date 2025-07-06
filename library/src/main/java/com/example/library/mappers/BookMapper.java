package com.example.library.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.DescribedBookDTO;
import com.example.library.model.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
	
	BookDTO toDTO(Book book);
	
	DescribedBookDTO toDescribedDTO(Book book);
	
	Book toEntity(DescribedBookDTO dto);
}
