package com.store.cashier.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.cashier.model.Cashier;
import com.store.cashier.repository.CashierRepository;
import com.store.cashier.service.CashierService;

@RestController
@RequestMapping("/cashiers")
public class CashierController {
	
	@Autowired
	private CashierService cashierService;
	
	@Autowired
	private CashierRepository cashierRepository;
	
	
	@GetMapping
	public Optional<Cashier> listCashiers () {
		return cashierRepository.findByStatusOpen();
	}
	

	@PostMapping
	public Cashier openingCashier () {
		return cashierService.openCashier();
	}
	

}
