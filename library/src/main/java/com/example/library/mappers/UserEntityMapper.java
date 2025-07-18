package com.example.library.mappers;

import com.example.library.model.User;
import com.example.library.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    public UserEntity toEntity(User model);

    public User toModel(UserEntity entity);
}
