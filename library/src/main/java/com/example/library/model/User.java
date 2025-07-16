package com.example.library.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class User {
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String username;
    @NotBlank
    @Size(max = 255)
    private String password;
    @Email
    @Size(max = 255)
    private String email;
}
