package com.store.address.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.address.input.AddressInput;
import com.store.address.model.Address;
import com.store.city.model.City;

@Component
public class AddressDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Address toDomainObjec (AddressInput addressInput) {
		return modelMapper.map(addressInput, Address.class);
	}
	
	
	public void copyToDomainObject (AddressInput addressInput, Address address) {
		//Evita exceção
		address.setCity(new City());
		
		modelMapper.map(addressInput, address);
	}

}
