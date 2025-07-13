package com.example.library.repositories;

import com.example.library.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    @Query("SELECT ae " +
            "FROM Author ae " +
            "WHERE ae.firstName=:firstName AND ae.middleName=:middleName AND ae.lastName=:lastName")
    Optional<AuthorEntity> findByName(
            @Param(value = "firstName") String firstName,
            @Param(value = "middleName") String middleName,
            @Param(value = "lastName") String lastName);
}
