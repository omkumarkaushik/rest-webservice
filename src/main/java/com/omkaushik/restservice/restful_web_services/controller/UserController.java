package com.omkaushik.restservice.restful_web_services.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omkaushik.restservice.restful_web_services.dao.UserDao;
import com.omkaushik.restservice.restful_web_services.model.User;
import org.springframework.web.bind.annotation.PathVariable;

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
		return dao.getUserById(id);
		

	}
	
	
}
