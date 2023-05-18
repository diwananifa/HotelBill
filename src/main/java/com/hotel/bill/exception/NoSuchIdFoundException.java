package com.hotel.bill.exception;

public class NoSuchIdFoundException extends RuntimeException {
	
	String message="id is not found";

	public NoSuchIdFoundException(String message) {

		this.message = message;
	}
	
	public NoSuchIdFoundException() {
		
	}
}
