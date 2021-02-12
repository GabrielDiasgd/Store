package com.store.city.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityIdInput {
	
	@NotNull
	private Long id;

}
