package com.example.library.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthorDTO(
		@NotBlank
		@Size(max = 255)
		@Pattern(regexp = "^[A-Z][a-z]+$")
		String firstName,
		@NotBlank
		@Size(max = 255)
		@Pattern(regexp = "^[A-Z][a-z]+$")
		String middleName, 
		@NotBlank
		@Size(max = 255)
		@Pattern(regexp = "^[A-Z][a-z]+$")
		String lastName
) {}
