package com.omkaushik.restservice.restful_web_services.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFindException extends RuntimeException {

	public UserNotFindException(String message) {
		super(message);
	}
	
}
