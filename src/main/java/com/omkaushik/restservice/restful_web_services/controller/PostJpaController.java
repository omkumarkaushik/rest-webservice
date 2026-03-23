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

import com.omkaushik.restservice.restful_web_services.PostRepository.PostRepository;
import com.omkaushik.restservice.restful_web_services.UserRepository.UserRepository;
import com.omkaushik.restservice.restful_web_services.dao.UserDao;
import com.omkaushik.restservice.restful_web_services.model.Post;
import com.omkaushik.restservice.restful_web_services.model.User;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PostJpaController {

	private UserDao dao;
	
	private UserRepository userRepository;
	
	private PostRepository repository;

	public PostJpaController(UserDao dao, UserRepository userRepository, PostRepository repository) {
		super();
		this.dao = dao;
		this.repository = repository;
		this.userRepository = userRepository;
	}
	
	@PostMapping("/jpa/users/{id}/post")
	public ResponseEntity<Post> addPost(@PathVariable int id, @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFindException("id: "+id);
		}
		
		post.setUser(user.get());
		
		Post savedPost = repository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
//	@SuppressWarnings("unchecked")
//	@GetMapping(path = "/jpa/users")
//	public List<User> getUserList(){
////		return dao.getAllUsers();
//		return repository.findAll();
//	}
//	
//	@GetMapping(path = "/jpa/users/{id}")
//	public EntityModel<User> getspecificUser(@PathVariable Integer id) {
////		User user = dao.getUserById(id);
//		Optional<User> user = repository.findById(id);
//		
//		if(user.isEmpty()) {
//			throw new UserNotFindException("id: "+id);
//		}
//		
//		EntityModel<User> entityModel = EntityModel.of(user.get());
//		
//		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUserList());
//		
//		entityModel.add(link.withRel("all-usrrs"));
//		
//		return entityModel;
//	}
//	
//	@DeleteMapping(path = "/jpa/users/{id}")
//	public void deleteUser(@PathVariable Integer id) {
////		dao.getDeleteUserById(id);
//		repository.deleteById(id);
//	}
//	
//	@GetMapping(path = "/jpa/users/{id}/posts")
//	public List<Post> retrievePost(@PathVariable Integer id) {
//		Optional<User> user = repository.findById(id);
//		
//		if(user.isEmpty()) {
//			throw new UserNotFindException("id: "+id);
//		}
//		return user.get().getPosts();
//	}
//	
//	@PostMapping(path = "/jpa/users")
//	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
////		User addedUser = dao.saveUser(user);
//		User addedUser = (User) repository.save(user);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(addedUser.getId())
//				.toUri();
//		return ResponseEntity.created(location).build();
//	}
//	
	
}
