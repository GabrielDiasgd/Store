package com.store.exception;

public class PhoneNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public PhoneNotFoundException(String message) {
		super(message);
	}

	public PhoneNotFoundException(Long phoneId) {
		super(String.format("Phone with id %d not found", phoneId));
	}
}
