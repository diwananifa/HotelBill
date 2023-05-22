package com.hotel.bill.exception;

public class NoSuchEmailFoundException extends RuntimeException {
	String message="email not found";
	
	public NoSuchEmailFoundException() {

	}
	
	public NoSuchEmailFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
