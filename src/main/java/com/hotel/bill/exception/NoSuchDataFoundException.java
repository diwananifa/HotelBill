package com.hotel.bill.exception;
public class NoSuchDataFoundException extends RuntimeException{
 String message="id not found";
		public NoSuchDataFoundException(String message){
			  this.message=message;
		}
	  public NoSuchDataFoundException(){
			 
		}
	@Override
	public String getMessage() {
		
		return message;
	}
}
