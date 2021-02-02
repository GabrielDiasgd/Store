package com.store.exception;

public class ProfileNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public ProfileNotFoundException(String message) {
		super(message);
	}

	public ProfileNotFoundException(Long profileId) {
		super(String.format("Profife with id %d not found", profileId));
	}
}
