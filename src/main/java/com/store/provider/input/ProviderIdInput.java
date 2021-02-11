package com.store.provider.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderIdInput {
	
	@NotNull
	private Long id;

}
