package com.silverbarsmarketplace.application.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The {@code Order} class is the global Exception handler class for handling different exceptions.
 * @author Utkarsh Kumar
 */

@ControllerAdvice
public class ExceptionHandlerController {
	
   @ExceptionHandler(value =Exception.class)
   public ResponseEntity<Object> exception(Exception exception) {
   
	  exception.printStackTrace(); 
      return new ResponseEntity<>("Error Occured", HttpStatus.NOT_FOUND);
   }
}