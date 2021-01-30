package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProviderNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public ProviderNotFoundException(String message) {
		super(message);
	}
	
	public ProviderNotFoundException(Long providerId) {
		this(String.format("Provider with id %d not found", providerId));
	}

}
