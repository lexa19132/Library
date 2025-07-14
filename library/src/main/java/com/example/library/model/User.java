package com.example.library.model;

import lombok.Data;

import java.util.Set;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private Set<Role> roles;

    private String email;
}
