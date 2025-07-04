package com.example.library.model.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.example.library.model.Genre;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
	@SequenceGenerator(name = "book_id_sequence_generator", sequenceName = "book_id_sequence")
	private Long id;
	
	@Column(name = "isbn", length = 17, unique = true, nullable = false)
	private String isbn;
	
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "genre", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Genre genre;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@ElementCollection(fetch = FetchType.EAGER) //Может быть поменяю, а вообще Eager вполне подходит мне.
	@CollectionTable(name = "authors", joinColumns = {
		@JoinColumn(name = "author_id", referencedColumnName = "id", table = "authors")
	})
	@Fetch(value = FetchMode.JOIN)
	private Set<Author> authors = new HashSet<>();
	//Автором ожет быть несколько.
	
	public Book() {
		
	}
	
	public Book(Long id, String isbn, String name, Genre genre, String description, Set<Author> authors) {
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.genre = genre;
		this.description = description;
		this.authors = authors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		return Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return String.format("Book [id=%s, isbn=%s, name=%s, genre=%s, description=%s, authors=%s]", id, isbn, name, genre,
				description, authors);
	}
}
