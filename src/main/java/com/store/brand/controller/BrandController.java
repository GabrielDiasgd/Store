package com.store.brand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.brand.converters.BrandAssemblerDTO;
import com.store.brand.converters.BrandDisassemblerInput;
import com.store.brand.dto.BrandDTO;
import com.store.brand.input.BrandInput;
import com.store.brand.model.Brand;
import com.store.brand.repository.BrandRepository;
import com.store.brand.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BrandAssemblerDTO brandAssembler;
	
	@Autowired
	private BrandDisassemblerInput brandDisassembler;
	
	@GetMapping
	public List<BrandDTO> listBrands () {
		return brandAssembler.toCollectionDTO(brandRepository.findAll());
	}
	
	@GetMapping("/{brandId}")
	private BrandDTO find (@PathVariable Long brandId) {
		return brandAssembler.toDTO(brandService.find(brandId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BrandDTO add (@RequestBody @Valid BrandInput brandInput) {
		Brand brand = brandDisassembler.toDomainObject(brandInput);
		return brandAssembler.toDTO(brandService.save(brand));
	}
	
	@PutMapping("/{brandId}")
	public BrandDTO update (@PathVariable Long brandId, @RequestBody BrandInput brandInput) {
		Brand brand = brandService.find(brandId);
		brandDisassembler.copyToDomainObject(brandInput, brand);
		return brandAssembler.toDTO(brandService.save(brand));
	}
	
	@DeleteMapping("/{brandId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long brandId) {
		brandService.delete(brandId);
	}
	
	
	
	
	
	
	
	

}
