package com.hotel.bill.exception;

public class DuplicateEmailFoundException extends RuntimeException{

	
	public String getMessage() {
		return "Email already registered";
	}

	
}
