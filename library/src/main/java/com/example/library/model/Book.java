package com.example.library.model;

import java.util.Set;

import lombok.Data;

@Data
public class Book {
	
	private Long id;
	
	private String name;
	
	private Genre genre;
	
	private String isbn;
	
	private String description;
	
	private Set<Author> authors;
}
