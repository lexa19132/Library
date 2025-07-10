package com.example.library.model.DTO;

import java.util.Set;

import com.example.library.model.Genre;
import com.example.library.model.DTO.validation.CreateValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record BookDTO(
		@Null(
				message = "No id should be provided in DTO while trying to create a new entity instance", 
				groups = CreateValidationGroup.class
		)
		Long id,
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
