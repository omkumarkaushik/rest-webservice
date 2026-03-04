package com.omkaushik.restservice.restful_web_services.exceptions;

import java.time.LocalDateTime;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.omkaushik.restservice.restful_web_services.controller.UserNotFindException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final @Nullable ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) throws Exception {
		
		ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UserNotFindException.class)
	public final @Nullable ResponseEntity<ErrorDetail> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		
		ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), "Total Error: " + ex.getFieldErrorCount() + " First Error: " + ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST);
		
	}
	
}
