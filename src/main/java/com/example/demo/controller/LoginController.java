package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.UsersModel;
import com.example.demo.service.UserService;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<User> registration(@RequestBody UsersModel request) {
		User user = userService.registration(request);
		UsersModel usersModel = new UsersModel();
		usersModel.setEmail(user.getEmail());
		usersModel.setEnabled(user.isEnabled());
		usersModel.setFullname(user.getFullname());
		usersModel.setId(user.getId());
		usersModel.setRoleName(user.getRoles().toString());
		return new ResponseEntity(usersModel, HttpStatus.OK);
	}

	@GetMapping(value = "/signin")
	public ResponseEntity<HttpStatus> signin() {
		return new ResponseEntity("Login Success Full", HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<HttpStatus> home(){
		return new ResponseEntity("Hi Guy! Welcome to Spring Boots With MongoDB Application Demo!",HttpStatus.OK);
	}
}
