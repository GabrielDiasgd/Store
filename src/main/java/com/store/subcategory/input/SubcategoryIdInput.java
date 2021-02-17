package com.store.subcategory.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubcategoryIdInput {
	
	@NotNull
	private Long id;

}
