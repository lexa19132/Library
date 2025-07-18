package com.example.library.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @Null
        Long id,
        @NotBlank
        @Size(max = 255)
        String username,
        @NotBlank
        @Size(max = 255)
        String password,
        @Size(max = 255)
        @Email
        String email
) {}
