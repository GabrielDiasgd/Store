package com.store.brand.input;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandInput {
	
	
	@NotEmpty
	private String name;

}
