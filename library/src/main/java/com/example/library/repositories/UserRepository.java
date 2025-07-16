package com.example.library.repositories;

import com.example.library.model.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    @Query("SELECT ue FROM User ue WHERE ue.username = :username")
    Optional<UserEntity> findByUsername(@Param(value = "username") String username);
}
