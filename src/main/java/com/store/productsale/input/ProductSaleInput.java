package com.store.productsale.input;

import javax.validation.constraints.NotEmpty;

import com.store.Product.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSaleInput {

	@NotEmpty
	private Long quantity;
	
	private Product product;

}
