package com.store.sale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.sale.converters.SaleAssemblerDTO;
import com.store.sale.converters.SaleDisassemblerInput;
import com.store.sale.dto.SaleDTO;
import com.store.sale.input.SaleInput;
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
	
	@Autowired
	private SaleAssemblerDTO saleAssembler;
	
	@Autowired
	private SaleDisassemblerInput saleDisassembler;
	
	@GetMapping
	public List<SaleDTO> listSales () {
		return saleAssembler.toCollectionDTO(saleRepository.findAll());
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SaleDTO add (@RequestBody @Valid SaleInput saleInput) {
		Sale sale = saleDisassembler.toDomainObject(saleInput);
		return saleAssembler.toDTO(saleService.save(sale));
	}

}
