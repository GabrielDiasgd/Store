package com.store.subcategory.controller;

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

import com.store.exception.BusinessException;
import com.store.exception.CategoryNotFoundException;
import com.store.subcategory.converters.SubcategoryAssemblerDTO;
import com.store.subcategory.converters.SubcategoryDisassemblerInput;
import com.store.subcategory.dto.SubCategoryDTO;
import com.store.subcategory.input.SubcategoryInput;
import com.store.subcategory.model.Subcategory;
import com.store.subcategory.repository.SubcategoryRepository;
import com.store.subcategory.service.SubcategoryService;

@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private SubcategoryAssemblerDTO subcategoryAssembler;
	
	@Autowired
	private SubcategoryDisassemblerInput subcategoryDisassembler;
	
	@GetMapping
	public List<SubCategoryDTO> listSubcategories () {
		return subcategoryAssembler.toCollectionModel(subcategoryRepository.findAll());
	}
	
	@GetMapping("/{subcategoryId}")
	public SubCategoryDTO find (@PathVariable Long subcategoryId) {
		return subcategoryAssembler.toDTO(subcategoryService.find(subcategoryId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SubCategoryDTO add (@RequestBody @Valid SubcategoryInput subcategoryInput) {
		try {
			Subcategory subcategory = subcategoryDisassembler.toDomainObject(subcategoryInput);
			return subcategoryAssembler.toDTO(subcategoryService.save(subcategory));
			
		} catch (BusinessException e) {
			throw new CategoryNotFoundException(e.getMessage());
		}
		
	}
		
	@PutMapping("/{subcategoryId}")
	public SubCategoryDTO update (@PathVariable Long subcategoryId, @RequestBody @Valid SubcategoryInput subcategoryInput) {
		try {
			Subcategory subcategory = subcategoryService.find(subcategoryId);
			subcategoryDisassembler.copyToDomainObject(subcategoryInput, subcategory);
			return subcategoryAssembler.toDTO(subcategoryService.save(subcategory));
		} catch (BusinessException e) {
			throw new CategoryNotFoundException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{subcategoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long subcategoryId) {
		subcategoryService.delete(subcategoryId);
	}
		
	}
	
	
	
	





















