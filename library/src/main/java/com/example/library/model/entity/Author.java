package com.example.library.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	name = "authors", 
	uniqueConstraints= @UniqueConstraint(
		columnNames = {"firstName", "middleName", "lastName"}
	)
)
public class Author {
	
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
	
	public Author() {}

	public Author(Long id, String firstName, String middleName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middelName) {
		this.middleName = middelName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		Author other = (Author) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return String.format("Author [id=%s, firstName=%s, middleName=%s, lastName=%s]", id, firstName, middleName,
				lastName);
	}	
}
