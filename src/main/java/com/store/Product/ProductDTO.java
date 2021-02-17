package com.store.Product;

import java.math.BigDecimal;

import com.store.brand.dto.BrandDTO;
import com.store.category.dto.CategoryDTO;
import com.store.provider.dto.SimpleProviderDTO;
import com.store.subcategory.dto.SubCategoryDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	//modelo de representação
	private String name;
	private String description;
	private Long stock;
	private BigDecimal price;
	private BigDecimal buyPrice;
	
	private CategoryDTO category;

	private SimpleProviderDTO provider;
	
	private BrandDTO brand;
	
	private SubCategoryDTO subcategory;
}
