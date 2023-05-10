package com.onerivet.deskbook.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onerivet.deskbook.models.response.ErrorMessageResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResorceNotFoundException.class)
	public ResponseEntity<ErrorMessageResponse> resourceNotFoundExceptionHandler(ResorceNotFoundException exception) {
		 ErrorMessageResponse response = new ErrorMessageResponse(LocalDateTime.now(), exception.getMessage());
	
		 return new ResponseEntity<ErrorMessageResponse>(response, HttpStatus.NOT_FOUND);
	}
}
