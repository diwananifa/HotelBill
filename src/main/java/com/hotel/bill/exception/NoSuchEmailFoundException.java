package com.hotel.bill.exception;

public class NoSuchEmailFoundException extends RuntimeException {

	public String getMessage() {
		return "email not found";
	}
	
}
