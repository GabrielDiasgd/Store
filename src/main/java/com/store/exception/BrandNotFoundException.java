package com.store.exception;

public class BrandNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public BrandNotFoundException(String message) {
		super(message);
	}
	
	public BrandNotFoundException(Long brandId) {
		super(String.format("Brand with id %d not found.", brandId));
	}

}
