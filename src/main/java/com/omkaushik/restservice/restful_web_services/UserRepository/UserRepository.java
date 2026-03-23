package com.omkaushik.restservice.restful_web_services.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkaushik.restservice.restful_web_services.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
