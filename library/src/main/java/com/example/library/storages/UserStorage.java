package com.example.library.storages;

import com.example.library.repositories.UserRepository;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@Value
public class UserStorage implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> {
                   return new org.springframework.security.core.userdetails.User(
                                    user.getUsername(),
                                    user.getPassword(),
                                    Set.of(user.getRole()));
                })
                .orElseThrow(() -> new UsernameNotFoundException("No such user with username: " + username));
    }
}
