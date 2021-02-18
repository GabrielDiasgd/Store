package com.store.brand.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.brand.dto.BrandDTO;
import com.store.brand.model.Brand;

@Component
public class BrandAssemblerDTO {

	@Autowired
	private ModelMapper modelMapper;
	
	public BrandDTO toDTO (Brand brand) {
		return modelMapper.map(brand, BrandDTO.class);
	}
	
	public List<BrandDTO> toCollectionDTO (List<Brand> brands) {
		return brands.stream()
				.map(brand -> toDTO(brand))
				.collect(Collectors.toList());
	}
}
