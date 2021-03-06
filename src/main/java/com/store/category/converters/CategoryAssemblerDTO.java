package com.store.category.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.category.dto.CategoryDTO;
import com.store.category.model.Category;

@Component
public class CategoryAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryDTO toDTO (Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}
	
	public List<CategoryDTO> toCollectionDTO (List<Category> categories) {
		return categories.stream()
				.map(category -> toDTO(category))
				.collect(Collectors.toList());
	}

}
