package com.store.Product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.Product.model.Product;
import com.store.Product.repository.ProductRepository;
import com.store.brand.model.Brand;
import com.store.brand.service.BrandService;
import com.store.category.model.Category;
import com.store.category.service.CategoryService;
import com.store.exception.EntityInUseException;
import com.store.exception.ProductNotFoundException;
import com.store.provider.model.Provider;
import com.store.provider.service.ProviderService;
import com.store.subcategory.model.Subcategory;
import com.store.subcategory.service.SubcategoryService;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	
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
		
		Brand brand = brandService.find(product.getBrand().getId());
		product.setBrand(brand);
		
		Subcategory subcategory = subcategoryService.find(product.getSubcategory().getId());
		product.setSubcategory(subcategory);
		
		return productRepository.save(product);
	}

	@Transactional
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





