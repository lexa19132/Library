package com.example.library.storages;

import com.example.library.mappers.UserDTOMapper;
import com.example.library.mappers.UserEntityMapper;
import com.example.library.model.User;
import com.example.library.model.entity.UserEntity;
import com.example.library.repositories.UserRepository;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Value
public class UserStorage {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    UserDTOMapper userDTOMapper;
    UserEntityMapper userEntityMapper;

    public void createAndRegisterUser(User user) {
       UserEntity result = userRepository.save(userEntityMapper.toEntity(user));
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
       //В теории тут будет сделан запрос в бд за ролью пользователя, немного странно получается, на этом куске 2 запроса.
    }

}
