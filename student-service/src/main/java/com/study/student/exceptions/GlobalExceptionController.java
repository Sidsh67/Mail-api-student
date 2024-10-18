package com.study.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionController {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(IdInvalidException.class)
	public ResponseEntity<?> idInvalidExceptionhandler(IdInvalidException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse( HttpStatus.NOT_FOUND.value(), 
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		

	}

	

}
