package com.example.library.storages;

import com.example.library.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserStorage implements UserDetailsService {

    private final UserRepository userRepository;

    public UserStorage(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return (UserDetails) userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
