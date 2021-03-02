package com.store.exception;

public class FormPaymentNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public FormPaymentNotFoundException(String message) {
		super(message);
	}
	
	public FormPaymentNotFoundException(Long formPayment) {
		super(String.format("Form Payment with id %d not found.", formPayment));
	}

}
