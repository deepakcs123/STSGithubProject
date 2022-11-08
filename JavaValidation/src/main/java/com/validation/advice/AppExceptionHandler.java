package com.validation.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.validation.services.AppRuntimeException;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> HandleExceptionArgument(MethodArgumentNotValidException exp) {
		Map<String, String> errorMap = new HashMap<>();
		exp.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});

		return errorMap;
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException excep) {
		return new ResponseEntity<String>("User not found for a given Id", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException excep) {
		return new ResponseEntity<String>("Id shouldnot be null", HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException excep) {
		return new ResponseEntity<String>(("User Not found for ID "+excep.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AppRuntimeException.class)
	public ResponseEntity<String> handleAppRuntimeException(AppRuntimeException excep) {
		return new ResponseEntity<String>(("Custom"+excep.getErrorMsg()),HttpStatus.BAD_REQUEST);
	}
}
