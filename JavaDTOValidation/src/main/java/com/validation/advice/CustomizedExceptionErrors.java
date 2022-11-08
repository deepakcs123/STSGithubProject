package com.validation.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.validation.exceptions.PowercutException;

@RestControllerAdvice
public class CustomizedExceptionErrors {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});

		return errorMap;
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {

		return new ResponseEntity<String>("Enter valid id", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<String>("method not supported for URL", HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(PowercutException.class)
	public ResponseEntity<String> handlePowercutException(PowercutException ex) {
		return new ResponseEntity<String>((ex.getErrorCode() + ": " + ex.getErrorMsg()), HttpStatus.BAD_REQUEST);
	}
}
