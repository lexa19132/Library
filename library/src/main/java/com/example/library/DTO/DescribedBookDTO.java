package com.example.library.DTO;

import java.util.Set;

import com.example.library.model.Genre;
import com.example.library.model.entity.Author;

public record DescribedBookDTO(Long id, String isbn, String name, Genre genre, String description, Set<Author> authors) {

}
