package com.omkaushik.restservice.restful_web_services.PostRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkaushik.restservice.restful_web_services.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
