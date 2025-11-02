package com.example.carrental.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>>handleNotFound(ResourceNotFoundException ex)
	{
		return error(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(BusinessValidationException.class)
	public ResponseEntity<Map<String, Object>>handleBusiness(BusinessValidationException ex)
	{
		return error(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>>handleValidation(MethodArgumentNotValidException ex)
	{
		return error(HttpStatus.BAD_REQUEST, "validation Failed");
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>>handleIllegalArgument(IllegalArgumentException ex)
	{
		return error(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>>handleIGeneric(Exception ex)
	{
		return error(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
	}
	
	private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message)
	{
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", Instant.now().toString());
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", message);
		return ResponseEntity.status(status).body(body);	
	}
	
}
