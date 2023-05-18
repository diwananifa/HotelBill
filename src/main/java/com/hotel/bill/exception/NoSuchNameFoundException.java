package com.hotel.bill.exception;

public class NoSuchNameFoundException extends RuntimeException {

	String message="name is not found";

	public String getMessage() {
		return message;
	}
	
	public NoSuchNameFoundException(String message) {
		this.message = message;
	}
	
	public NoSuchNameFoundException () {
		
	}
	
}
