package com.example.library.DTO;

import java.util.Set;

import com.example.library.model.Genre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookDTO(			
		Long id,
		@NotEmpty
		@Size(min = 17, max = 17)
		String isbn, 
		@NotEmpty         
		@Size(max = 255)String name, 
		Genre genre, 
		@NotNull
		String description, 
		@Valid
		@Size(min = 1)
		Set<AuthorDTO> authors
) {}
