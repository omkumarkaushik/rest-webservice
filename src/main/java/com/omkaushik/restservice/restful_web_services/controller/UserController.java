package com.omkaushik.restservice.restful_web_services.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.omkaushik.restservice.restful_web_services.dao.UserDao;
import com.omkaushik.restservice.restful_web_services.model.User;

import jakarta.validation.Valid;

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
	public EntityModel<User> getspecificUser(@PathVariable Integer id) {
		User user = dao.getUserById(id);
		
		if(user == null) {
			throw new UserNotFindException("id: "+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUserList());
		
		entityModel.add(link.withRel("all-usrrs"));
		
		return entityModel;
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		dao.getDeleteUserById(id);
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User addedUser = dao.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
}
