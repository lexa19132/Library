package com.example.library.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.example.library.model.Book;
import com.example.library.model.DTO.BookDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorDTOMapper.class})
public interface BookDTOMapper {
	
	 Book toModel(BookDTO dto);

	 BookDTO toDTO(Book model);
}
