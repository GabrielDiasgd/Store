package com.store.Product.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.Product.ProductDTO;
import com.store.Product.model.Product;

@Component
public class ProductAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDTO toDTO (Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	
	public List<ProductDTO> toCollectionDTO (List<Product> products) {
		return products.stream()
				.map(product -> toDTO(product))
				.collect(Collectors.toList());
	}

}
