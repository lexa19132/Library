package com.example.library.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity(name = "Author")
@Table(
	name = "authors", 
	uniqueConstraints= @UniqueConstraint(
		columnNames = {"firstName", "middleName", "lastName"}
	)
)
public class AuthorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "firstName", nullable = false, length = 255)
	private String firstName;
	
	@Column(name = "middleName", nullable = false, length = 255)
	private String middleName;
	
	@Column(name = "lastName", nullable = false, length = 255)
	private String lastName;
}
