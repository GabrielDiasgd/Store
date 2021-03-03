package com.store.cashier.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.cashier.model.Cashier;
import com.store.cashier.repository.CashierRepository;

@Service
public class CashierService {
	
	@Autowired
	private CashierRepository cashierRepository;
	
	@Transactional
	public Cashier openCashier() {
		Cashier cashier = new Cashier();
		cashier.openCashier();
		
		return cashierRepository.save(cashier);
	}

}
