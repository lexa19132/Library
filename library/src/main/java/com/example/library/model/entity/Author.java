package com.example.library.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;

//Я не помню надо ли в JPA вешать сюда Embeddedable, поэтому потом займусь этим.
public class Author {
	
	private Long id;
	
	@Column(name = "firstName", length = 255, nullable = false)
	private String firstName;
	
	@Column(name = "middleName", length = 255, nullable = false)
	private String middleName;
	
	@Column(name = "lastName", length = 255, nullable = false)
	private String lastName;
	
	public Author() {
		
	}

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

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
