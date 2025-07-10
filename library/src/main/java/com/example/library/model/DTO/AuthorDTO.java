package com.example.library.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthorDTO(
		Long id,
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
//Я засунул в автора айди, это мне кажется немного неправильно, но иначе надо писать некую логику в маппере.
