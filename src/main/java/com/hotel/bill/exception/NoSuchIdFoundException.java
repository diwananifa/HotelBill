package com.hotel.bill.exception;

public class NoSuchIdFoundException extends RuntimeException {

	public String getMessage() {
		return "No ID Found";
	}
	
}
