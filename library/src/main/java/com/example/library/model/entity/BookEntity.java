package com.example.library.model.entity;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Book")
@Table(name = "books")
public class BookEntity {
	
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
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name = "books_authors",
		joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
	)
	private Set<AuthorEntity> authors = new HashSet<>();
}