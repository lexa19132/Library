package com.example.library.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthorDTO(
		@NotEmpty
		@Size(max = 255)
		@Pattern(regexp = "^[A-Z][a-z]+$")
		String firstName,
		@NotEmpty
		@Size(max = 255)
		@Pattern(regexp = "^[A-Z][a-z]+$")
		String middleName, 
		@NotEmpty
		@Size(max = 255)
		@Pattern(regexp = "^[A-Z][a-z]+$")
		String lastName
) {}
