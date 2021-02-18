package com.store.subcategory.input;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.store.category.input.CategoryIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubcategoryInput {

	@NotEmpty
	private String name;
	
	@Valid
	@NotNull
	private CategoryIdInput category;
}
