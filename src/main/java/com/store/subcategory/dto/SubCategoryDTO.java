package com.store.subcategory.dto;

import com.store.category.dto.CategoryDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryDTO {
	
	private Long id;
	
	private String name;
	
	private CategoryDTO category;
	

}
