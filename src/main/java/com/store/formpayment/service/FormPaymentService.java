package com.store.formpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.exception.FormPaymentNotFoundException;
import com.store.formpayment.model.FormPayment;
import com.store.formpayment.repository.FormPaymentRepository;

@Service
public class FormPaymentService {
	
	@Autowired
	private FormPaymentRepository formPaymentRepository;
	
	
	public FormPayment find (Long formPaymentId) {
		return formPaymentRepository.findById(formPaymentId)
				.orElseThrow(() -> new FormPaymentNotFoundException(formPaymentId));
	}

}
