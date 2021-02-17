package com.store.category.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.category.input.CategoryInput;
import com.store.category.model.Category;

@Component
public class CategoryDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Category toDomainObject (CategoryInput categoryInput) {
		return modelMapper.map(categoryInput, Category.class);
	}
	
	public void copyToDomainObject (CategoryInput categoryInput, Category category) {
		modelMapper.map(categoryInput, category);
	}

}
