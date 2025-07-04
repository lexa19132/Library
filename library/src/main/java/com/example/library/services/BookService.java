package com.example.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository BookRepository;
	//Пока что так
}
