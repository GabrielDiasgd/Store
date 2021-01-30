package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(Long userId) {
		this(String.format("User with id %d not found", userId));
	}

}
