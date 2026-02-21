package com.omkaushik.restservice.restful_web_services.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omkaushik.restservice.restful_web_services.dao.UserDao;
import com.omkaushik.restservice.restful_web_services.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

	private UserDao dao;

	public UserController(UserDao dao) {
		super();
		this.dao = dao;
	}
	
	@GetMapping(path = "/users")
	public List<User> getUserList(){
		return dao.getAllUsers();
	}
	
	@GetMapping(path = "/users/{id}")
	public User getspecificUser(@PathVariable Integer id) {
		User user = dao.getUserById(id);
		
		if(user == null) {
			throw new UserNotFindException("id: "+id);
		}
		return user;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User addedUser = dao.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
}
