package com.store.subcategory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.category.model.Category;
import com.store.category.service.CategoryService;
import com.store.exception.BusinessException;
import com.store.exception.SubcategoryNotFoundException;
import com.store.subcategory.model.Subcategory;
import com.store.subcategory.repository.SubcategoryRepository;

@Service
public class SubcategoryService {
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Subcategory find (Long subcategoryId) {
		return subcategoryRepository.findById(subcategoryId)
				.orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
	}
	
	@Transactional
	public Subcategory save (Subcategory subcategory) {
		Category category = categoryService.find(subcategory.getCategory().getId());
		subcategory.setCategory(category);
		
		return subcategoryRepository.save(subcategory);
	}
	
	@Transactional
	public void delete (Long subcategoryId) {
		try {
			subcategoryRepository.deleteById(subcategoryId);
			subcategoryRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new SubcategoryNotFoundException(subcategoryId);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException(String.format("Subcategory with id %d cannot be removed, because it's in use.", subcategoryId));
		}
	}

}
