package com.store.brand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.brand.model.Brand;
import com.store.brand.repository.BrandRepository;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping
	public List<Brand> listBrands () {
		return brandRepository.findAll();
	}

}
