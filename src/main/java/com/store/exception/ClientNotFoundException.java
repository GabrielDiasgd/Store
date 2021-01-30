package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String message) {
		super(message);
	}
	
	public ClientNotFoundException(Long clientId) {
		this(String.format("Client with id %d not found", clientId));
	}

}
