package com.store.subcategory.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.subcategory.dto.SubCategoryDTO;
import com.store.subcategory.model.Subcategory;

@Component
public class SubcategoryAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SubCategoryDTO toDTO (Subcategory subcategory) {
		return modelMapper.map(subcategory, SubCategoryDTO.class);
	}
	
	public List<SubCategoryDTO> toCollectionModel (List<Subcategory> subcategories) {
		return subcategories.stream()
				.map(subcategory -> toDTO(subcategory))
				.collect(Collectors.toList());
	}

}
