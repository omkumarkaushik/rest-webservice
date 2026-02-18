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
	
	static {
		users.add(new User(1, "Arthur Morgan", LocalDate.now().minusYears(40)));
		users.add(new User(2, "John Marston", LocalDate.now().minusYears(35)));
		users.add(new User(3, "Dutch van-der Linde", LocalDate.now().minusYears(50)));
		users.add(new User(4, "Micah Bell", LocalDate.now().minusYears(38)));
		users.add(new User(5, "Hosea", LocalDate.now().minusYears(55)));
		users.add(new User(6, "Charles", LocalDate.now().minusYears(29)));
		users.add(new User(7, "Sadie Adler", LocalDate.now().minusYears(27)));
		users.add(new User(8, "Bill Williamson", LocalDate.now().minusYears(42)));
		users.add(new User(9, "Javier Escuella", LocalDate.now().minusYears(31)));
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
}
