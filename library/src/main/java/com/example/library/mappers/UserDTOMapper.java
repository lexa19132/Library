package com.example.library.mappers;

import com.example.library.model.DTO.UserDTO;
import com.example.library.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDTOMapper {

    UserDTO toDTO (User user);

    User toModel(UserDTO dto);
}
