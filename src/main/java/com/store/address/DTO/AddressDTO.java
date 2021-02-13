package com.store.address.DTO;

import com.store.city.DTO.CityDTO;

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
	
	private CityDTO city;
}
