package com.example.carrental.exceptions;

public class BusinessValidationException extends RuntimeException {
	
	private static final long serialVersionUID=1;
	
	public BusinessValidationException(String message)
	{
		super(message);
	}

}
