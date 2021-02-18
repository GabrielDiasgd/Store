package com.store.subcategory.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.subcategory.input.SubcategoryInput;
import com.store.subcategory.model.Subcategory;

@Component
public class SubcategoryDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Subcategory toDomainObject (SubcategoryInput subcategoryInput) {
		return modelMapper.map(subcategoryInput, Subcategory.class);
	}
	
	public void copyToDomainObject (SubcategoryInput subcategoryInput, Subcategory subcategory) {
		modelMapper.map(subcategoryInput, subcategory);
	}

}
