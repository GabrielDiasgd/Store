package com.store.address.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.address.input.AddressInput;
import com.store.address.model.Address;

@Component
public class AddressDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Address toDomainObjec (AddressInput addressInput) {
		return modelMapper.map(addressInput, Address.class);
	}

}
