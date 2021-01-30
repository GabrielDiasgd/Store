package com.store.Product.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

	@GetMapping
	public List<Category> listCategory () {
		return categoryRepository.findAll();
	}
	
	@GetMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.OK)
	public Category find (@PathVariable Long categoryId) {
		return categoryService.find(categoryId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category register (@RequestBody Category category) {
		return categoryRepository.save(category);
	}
	
	@PutMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.OK)
	public Category update (@PathVariable Long categoryId, @RequestBody Category category) {
		Category currentCategory = categoryService.find(categoryId);
		BeanUtils.copyProperties(category, currentCategory, "id", "dateCreation");
		categoryRepository.save(currentCategory);
		   	return currentCategory; 
	
	}
	
	
	@DeleteMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long categoryId) {
		categoryService.delete(categoryId);
	}
}
