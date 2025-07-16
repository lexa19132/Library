package com.example.library.model.DTO;

import java.util.Set;

import com.example.library.model.DTO.validation.UpdateValidationGroup;
import com.example.library.model.Genre;
import com.example.library.model.DTO.validation.CreateValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record BookDTO(
		@Null(
				message = "No id to create new entity",
				groups = CreateValidationGroup.class
		)
		@NotNull(
				message = "Provided id to alter existing entity",
				groups = UpdateValidationGroup.class
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
		@Valid
		Set<AuthorDTO> authors
) {}
