package com.store.exception;

public class PermissionNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(String message) {
		super(message);
	}
	
	public PermissionNotFoundException(Long permissionId) {
		super(String.format("Permission with id %d not found.", permissionId));
	}

}
