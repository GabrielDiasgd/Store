package com.store.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.sale.model.Sale;
import com.store.sale.repository.SaleRepository;
import com.store.sale.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public List<Sale> listSales () {
		return saleRepository.findAll();
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Sale add (@RequestBody Sale sale) {
		return saleService.save(sale);
	}

}
