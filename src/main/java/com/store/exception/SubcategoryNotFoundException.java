package com.store.exception;

public class SubcategoryNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public SubcategoryNotFoundException(String message) {
		super(message);
	}
	
	public SubcategoryNotFoundException(Long subcategoryId) {
		super(String.format("Subcategory with id %d not found.", subcategoryId));
	}

}
