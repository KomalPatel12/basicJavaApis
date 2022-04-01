package com.javademo.springboot.crudrestfulwebservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionId = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
