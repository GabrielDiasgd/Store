package com.store.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.category.model.Category;
import com.store.category.repository.CategoryRepository;
import com.store.exception.CategoryNotFoundException;
import com.store.exception.EntityInUseException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category find(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(
				() -> new CategoryNotFoundException(categoryId));
		
		return category;
	}

	@Transactional
	public void delete(Long categoryId) {
		try {
			categoryRepository.deleteById(categoryId);
			categoryRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CategoryNotFoundException(categoryId);
		}	catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d Category cannot be removed, because it is in use.", categoryId));
		}
	}
}
