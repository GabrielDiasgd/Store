package com.store.brand.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.brand.input.BrandInput;
import com.store.brand.model.Brand;

@Component
public class BrandDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public Brand toDomainObject(BrandInput brandInput) {
		return modelMapper.map(brandInput, Brand.class);
	}
	
	
	public void copyToDomainObject (BrandInput brandInput, Brand brand) {
		modelMapper.map(brandInput, brand);
	}

}
