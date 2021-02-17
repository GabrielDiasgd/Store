package com.store.category.input;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInput {
	
	@NotEmpty
	private String description;

}
