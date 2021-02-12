package com.store.address.DTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.store.city.input.CityIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

	private String street;
	private Integer number;
	private String neighborhood;
	private String complement;
	private String cep;
	
	@Valid
	@NotNull
	private CityIdInput city;
}
