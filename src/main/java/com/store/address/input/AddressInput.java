package com.store.address.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.store.city.input.CityIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {

	private String street;
	private Integer number;
	private String neighborhood;
	private String complement;
	private String cep;

	@Valid
	@NotNull
	private CityIdInput city;

}
