package com.hotel.bill.exceptionhandler;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hotel.bill.exception.DuplicateEmailFoundException;
import com.hotel.bill.exception.NoSuchDataFoundException;
import com.hotel.bill.exception.NoSuchEmailFoundException;
import com.hotel.bill.exception.NoSuchIdFoundException;
import com.hotel.bill.exception.NoSuchNameFoundException;
import com.hotel.bill.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationContoller extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ObjectError> errors = ex.getAllErrors();
		Map<String, String> map = new LinkedHashMap();
		for (ObjectError error : errors) {
			String message = error.getDefaultMessage();
			String field = ((FieldError) error).getField();
			map.put(field, message);
		}

		ResponseStructure<Map<String, String>> structure = new ResponseStructure<>();
		structure.setMessage(HttpStatus.BAD_REQUEST.name());
		structure.setStatuscode(HttpStatus.BAD_REQUEST.value());
		structure.setData(map);

		// ResponseEntity<ResponseStructure<Map<String,String>> entity=new

		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);

		// entity; ResponseEntity<> entity=
	}

	@ExceptionHandler(DuplicateEmailFoundException.class)
	public ResponseEntity<ResponseStructure<String>> duplicateEmailFoundException(DuplicateEmailFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		structure.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
		structure.setMessage("duplicate email");
		structure.setData(e.getMessage());

		return entity;
	}

	@ExceptionHandler(NoSuchIdFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchIdFoundException(NoSuchIdFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);

		structure.setMessage("Not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		structure.setData(e.getMessage());

		return entity;
	}
	
	@ExceptionHandler(NoSuchNameFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchNameFoundException(NoSuchNameFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);

		structure.setMessage("Not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		structure.setData(e.getMessage());

		return entity;
	}

	@ExceptionHandler(NoSuchDataFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchDataFoundException(NoSuchDataFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<ResponseStructure<String>>(structure,
				HttpStatus.NOT_FOUND);

		structure.setMessage("no data present");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		structure.setData(e.getMessage());

		return entity;
	}

	@ExceptionHandler(NoSuchEmailFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchEmailFoundException(NoSuchEmailFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		ResponseEntity<ResponseStructure<String>> entity = new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);

		structure.setMessage("Not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		structure.setData(e.getMessage());

		return entity;
	}
}
