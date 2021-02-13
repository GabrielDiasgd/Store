package com.store.Product.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.Product.input.ProductInput;
import com.store.Product.model.Category;
import com.store.Product.model.Product;
import com.store.provider.model.Provider;

@Component
public class ProductDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public Product toDomainObject (ProductInput productInput) {
		return modelMapper.map(productInput, Product.class);
	}
	
	
	public void copyToDomainObject (ProductInput productInput, Product product) {
		product.setProvider(new Provider());
		product.setCategory(new Category());
		
		modelMapper.map(productInput, product);
	}

}
