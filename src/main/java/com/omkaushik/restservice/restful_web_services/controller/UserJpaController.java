package com.omkaushik.restservice.restful_web_services.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public class UserJpaController {

	private UserDao dao;
	
	private JpaRepository repository;

	public UserJpaController(UserDao dao, JpaRepository repository) {
		super();
		this.dao = dao;
		this.repository = repository;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/jpa/users")
	public List<User> getUserList(){
//		return dao.getAllUsers();
		return repository.findAll();
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> getspecificUser(@PathVariable Integer id) {
//		User user = dao.getUserById(id);
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFindException("id: "+id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUserList());
		
		entityModel.add(link.withRel("all-usrrs"));
		
		return entityModel;
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
//		dao.getDeleteUserById(id);
		repository.deleteById(id);
	}
	
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
//		User addedUser = dao.saveUser(user);
		User addedUser = (User) repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
}
