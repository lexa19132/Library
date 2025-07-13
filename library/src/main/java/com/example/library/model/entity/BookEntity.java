package com.example.library.model.entity;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.library.model.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Book")
@Table(name = "books")
@NamedEntityGraph(
		name = "books_authors_entity_graph",
		attributeNodes = {@NamedAttributeNode(value = "authors")}
)
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
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "books_authors",
		joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
	)
	private Set<AuthorEntity> authors = new HashSet<>();
}