package com.simpledevelopment.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
		List<String> errors = new ArrayList<String>(e.getConstraintViolations().size());
		e.getConstraintViolations().forEach(error -> errors.add(error.toString()));
		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}
}
