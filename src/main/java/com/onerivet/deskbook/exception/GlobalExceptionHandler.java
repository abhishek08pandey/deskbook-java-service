package com.onerivet.deskbook.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.onerivet.deskbook.models.response.GenericResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<GenericResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {

		GenericResponse<?> genericResponse = new GenericResponse<>(null, exception.getMessage());

		return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GenericResponse<?>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		
		String defaultMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		
		GenericResponse<?> genericResponse = new GenericResponse<>(null, defaultMessage);

		return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<GenericResponse<?>> illegalArgumentExceptionHandler(IllegalArgumentException exception) {

		GenericResponse<?> genericResponse = new GenericResponse<>(null, exception.getMessage());

		return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<GenericResponse<?>> constraintViolationExceptionHandler(ConstraintViolationException exception) {

		GenericResponse<?> genericResponse = new GenericResponse<>(null, exception.getMessage());

		return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<GenericResponse<?>> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception) {

		GenericResponse<?> genericResponse = new GenericResponse<>(null, exception.getMessage());
		
		return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse<?>> exceptionHandler(Exception exception) {
		
		GenericResponse<?> genericResponse = new GenericResponse<>(null, exception.getMessage());
		
		return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
