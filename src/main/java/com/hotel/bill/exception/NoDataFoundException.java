package com.hotel.bill.exception;

public class NoDataFoundException extends RuntimeException{

	public String getMessage() {
		return "No data found";
	}
}
