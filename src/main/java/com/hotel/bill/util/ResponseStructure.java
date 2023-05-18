package com.hotel.bill.util;

import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
=======

@Getter
@Setter
public class ResponseStructure<T> {
	
	private int statuscode;
	private String message;
	private T data;
	
>>>>>>> 94085f5c2dda9ad186ee1113b6668d8172b4ef42

@Getter
@Setter
public class ResponseStructure<T> {
//after processing the data in the backend we must display the content in front end (status of the request)
//for that request our we use our Response structure 
	
	private int statuscode;
	private String message;
	private T data;//T-- it specify that we can send any non primitive data types(genric)
	
}
