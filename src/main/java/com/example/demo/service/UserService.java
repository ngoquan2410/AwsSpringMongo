package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.model.UsersModel;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public User registration(UsersModel request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setEnabled(true);
		user.setFullname(request.getFullname());
		user.setPassword(bCryptPasswordEncoder().encode(request.getPassword()));
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
	
	public User findUserByEmail(String userName) {
		return userRepository.findByEmail(userName);
	}
}
