package com.store.brand.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandIdInput {

	@NotNull
	private Long id;
}
