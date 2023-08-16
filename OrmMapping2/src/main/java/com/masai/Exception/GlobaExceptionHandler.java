package com.masai.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobaExceptionHandler {

	@ExceptionHandler(value = NoEmployeesFound.class)
	public ResponseEntity<ApiError> handlerNoEmployeeFoundException() {
		
		ApiError error= new ApiError(500, "No Employee Found In this id", new Date());
		
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
}
