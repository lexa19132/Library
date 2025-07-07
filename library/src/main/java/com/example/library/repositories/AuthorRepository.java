package com.example.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.library.model.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>{

	@Query("SELECT a FROM Author a WHERE a.firstName=:firstName AND a.middleName=:middleName AND a.lastName=:lastName")
	public Optional<Author> findByFullName(@Param(value = "firstName") String firstName, @Param(value = "middleName") String middleName, @Param(value = "lastName") String lastName);
}
