package com.store.Product.controller;

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

import com.store.Product.CategoryDTO;
import com.store.Product.converters.CategoryAssemblerDTO;
import com.store.Product.converters.CategoryDisassemblerInput;
import com.store.Product.input.CategoryInput;
import com.store.Product.model.Category;
import com.store.Product.repository.CategoryRepository;
import com.store.Product.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryAssemblerDTO categoryAssembler;
	
	@Autowired
	private CategoryDisassemblerInput categoryDisassembler;

	@GetMapping
	public List<CategoryDTO> listCategory () {
		return categoryAssembler.toCollectionDTO(categoryRepository.findAll());
	}
	
	@GetMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO find (@PathVariable Long categoryId) {
		return categoryAssembler.toDTO(categoryService.find(categoryId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryDTO register (@RequestBody @Valid CategoryInput categoryInput) {
		Category category = categoryDisassembler.toDomainObject(categoryInput);
		return categoryAssembler.toDTO(categoryRepository.save(category));
	}
	
	@PutMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO update (@PathVariable Long categoryId, @RequestBody @Valid CategoryInput categoryInput) {
		Category category = categoryService.find(categoryId);
		categoryDisassembler.copyToDomainObject(categoryInput, category);
		return categoryAssembler.toDTO(categoryRepository.save(category));
	}
	
	
	@DeleteMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long categoryId) {
		categoryService.delete(categoryId);
	}
}
