package com.example.library.model.entity;

import java.sql.Types;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.library.model.Genre;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence_generator")
	@SequenceGenerator(name = "book_id_sequence_generator", sequenceName = "book_id_sequence")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "isbn", length = 17, unique = true)
	private String isbn;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "genre")
	@Enumerated(value = EnumType.STRING)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	private Genre genre;
	
	@Column(name = "description")
	@Lob
	@JdbcTypeCode(value = Types.LONGNVARCHAR)
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinTable(
		name = "books_authors",
		joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
	)
	private Set<Author> authors = new HashSet<>();
	
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
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return String.format("Book [id=%s, isbn=%s, name=%s, genre=%s, description=%s, authors=%s]", id, isbn, name, genre, description, authors);
	}
}
