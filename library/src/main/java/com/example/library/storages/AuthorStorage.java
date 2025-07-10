package com.example.library.storages;

import org.springframework.stereotype.Component;

import com.example.library.mappers.AuthorDTOMapper;
import com.example.library.mappers.AuthorEntityMapper;
import com.example.library.model.Author;
import com.example.library.repositories.AuthorRepository;

@Component
public class AuthorStorage {
	
	private final AuthorRepository repository;
	
	private final AuthorEntityMapper entityMapper;
	
	private final AuthorDTOMapper DTOMapper;
	
	public AuthorStorage(AuthorRepository repository, AuthorEntityMapper entityMapper, AuthorDTOMapper DTOMapper) {
		this.repository = repository;
		this.entityMapper = entityMapper;
		this.DTOMapper = DTOMapper;
	}
	//Пока что пустой, пусть будет, я мб в мапперы с ним полезу.
}
