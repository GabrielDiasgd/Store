package com.store.Product.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryIdInput {
	
	@NotNull
	private Long id;

}
