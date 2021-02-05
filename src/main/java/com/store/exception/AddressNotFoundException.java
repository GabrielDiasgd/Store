package com.store.exception;

public class AddressNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String message) {
		super(message);
	}
	
	public AddressNotFoundException(Long addressId) {
		super(String.format("Address with id %d not found.", addressId));
	}

}
