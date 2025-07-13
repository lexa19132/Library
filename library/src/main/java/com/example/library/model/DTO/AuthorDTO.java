package com.example.library.model.DTO;

import com.example.library.model.DTO.validation.CreateValidationGroup;
import com.example.library.model.DTO.validation.UpdateValidationGroup;
import jakarta.validation.constraints.*;

public record AuthorDTO(
		@Null(
				message = "Author id should not be specified while creating/updating book",
				groups = {CreateValidationGroup.class, UpdateValidationGroup.class}
		)
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
