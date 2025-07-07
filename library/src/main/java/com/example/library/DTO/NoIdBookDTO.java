package com.example.library.DTO;

import java.util.Set;

import com.example.library.model.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoIdBookDTO(
		@NotBlank
		@Size(min = 17, max = 17)
		String isbn, 
		@NotBlank
		@Size(max = 255)
		String name, 
		@NotNull
		Genre genre, 
		String description, 
		@NotEmpty
		Set<AuthorDTO> authors
) {}
