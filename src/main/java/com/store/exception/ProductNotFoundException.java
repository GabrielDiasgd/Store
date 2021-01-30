package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(Long productId) {
		this(String.format("Product with id %d not found", productId));
	}

}
