package com.store.Product.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.store.brand.input.BrandIdInput;
import com.store.category.input.CategoryIdInput;
import com.store.provider.input.ProviderIdInput;
import com.store.subcategory.input.SubcategoryIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInput {

	//Modelo para inserção
	
	@NotEmpty
	private String name;
	private String description;
	
	private Long stock;
	
	@PositiveOrZero
	private BigDecimal price;
	
	@PositiveOrZero
	private BigDecimal buyPrice;
	
	@Valid
	@NotNull
	private CategoryIdInput category;

	@Valid
	@NotNull
	private ProviderIdInput provider;
	
	@Valid
	@NotNull
	private BrandIdInput brand;
	
	@Valid
	@NotNull
	private SubcategoryIdInput subcategory;
}
