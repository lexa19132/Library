package com.example.library.DTO;

import com.example.library.model.Genre;

public record BookDTO(Long id, String isbn, String name, Genre genre) {

}
