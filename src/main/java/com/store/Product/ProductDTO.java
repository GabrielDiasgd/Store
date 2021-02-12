package com.store.Product;

import java.math.BigDecimal;

import com.store.provider.dto.SimpleProviderDTO;

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
}
