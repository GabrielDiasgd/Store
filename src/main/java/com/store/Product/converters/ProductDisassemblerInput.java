package com.store.Product.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.Product.input.ProductInput;
import com.store.Product.model.Product;

@Component
public class ProductDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public Product toDomainObject (ProductInput productInput) {
		return modelMapper.map(productInput, Product.class);
	}

}
