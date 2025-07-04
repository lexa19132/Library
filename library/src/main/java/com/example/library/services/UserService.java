package com.example.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository UserRepository;
}
