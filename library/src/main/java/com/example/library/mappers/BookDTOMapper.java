package com.example.library.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import com.example.library.model.Book;
import com.example.library.model.DTO.BookDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorDTOMapper.class})
public interface BookDTOMapper {//У меня почему-то ругается здесь Eclipse, при этом все работает и в target все успешно собирается.
	
	public Book toModel(BookDTO dto);
	
	public BookDTO toDTO(Book model);
}
