package com.example.library.services;

import com.example.library.repositories.UserRepository;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Value
@Slf4j
public class UserDetailsServiceImplementation implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> {
                    log.info(user.toString());
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            Set.of(user.getRole()));
                })
                .orElseThrow(() -> new UsernameNotFoundException("No such user with username: " + username));
    }
}
