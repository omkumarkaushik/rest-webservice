package com.omkaushik.restservice.restful_web_services.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.omkaushik.restservice.restful_web_services.model.User;

@Component
public class UserDao {

	private static List<User> users = new ArrayList<>();
	
	private static Integer count = 0;
	
	static {
		users.add(new User(++count, "Arthur Morgan", LocalDate.now().minusYears(40)));
		users.add(new User(++count, "John Marston", LocalDate.now().minusYears(35)));
		users.add(new User(++count, "Dutch van-der Linde", LocalDate.now().minusYears(50)));
		users.add(new User(++count, "Micah Bell", LocalDate.now().minusYears(38)));
		users.add(new User(++count, "Hosea", LocalDate.now().minusYears(55)));
		users.add(new User(++count, "Charles", LocalDate.now().minusYears(29)));
		users.add(new User(++count, "Sadie Adler", LocalDate.now().minusYears(27)));
		users.add(new User(++count, "Bill Williamson", LocalDate.now().minusYears(42)));
		users.add(new User(++count, "Javier Escuella", LocalDate.now().minusYears(31)));
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	//Old approach
	public User getSpecificUser(Integer id) {
		for(User u : users) {
			if(u.getId() == id) {
				return u;
			}
		}
		return null;
	}
	
	//functional Programming approach
	public User getUserById(Integer id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().get();
	}
	
	public User saveUser(User user) {
		int id = ++count;
		user.setId(id);
		users.add(user);
		return user;
	}
}
