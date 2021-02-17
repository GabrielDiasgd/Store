package com.store.address.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.address.model.ViaCep;

@RestController
@RequestMapping("viacep.com.br/ws")
public class ViaCepController {
	
//	@Autowired
//	private ViaCepRepository viaCepRepository;
//	
	@GetMapping("/{cep}/json/")
	public String buscarCep (@PathVariable String cep) {
		ViaCep via = new ViaCep();
		return String.valueOf(via);
	}

}
