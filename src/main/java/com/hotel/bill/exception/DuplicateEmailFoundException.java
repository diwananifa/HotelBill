package com.hotel.bill.exception;

public class DuplicateEmailFoundException extends RuntimeException{
	String message="email is duplicated";
	
	public DuplicateEmailFoundException() {
		
	}

	public DuplicateEmailFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
