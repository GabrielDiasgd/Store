package com.store.Product.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.store.Product.model.Category;
import com.store.Product.model.Product;
import com.store.Product.repository.ProductRepository;
import com.store.exception.EntityInUseException;
import com.store.exception.ProductNotFoundException;
import com.store.provider.ProviderService;
import com.store.provider.model.Provider;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProviderService providerService;
	
	
	public Product find(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException( productId));
		return product;
	}
	
	@Transactional
	public Product save (Product product) {
		Category category = categoryService.find(product.getCategory().getId());
		product.setCategory(category);
		
		Provider provider = providerService.find(product.getProvider().getId());
		product.setProvider(provider);
		
		return productRepository.save(product);
	}

	public void delete (Long productId) {
		try {
			productRepository.deleteById(productId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException(productId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d Product cannot be removed, because it is in use.", productId));
		}
	}

}





