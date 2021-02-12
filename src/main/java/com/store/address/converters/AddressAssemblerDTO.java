package com.store.address.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.address.DTO.AddressDTO;
import com.store.address.model.Address;

@Component
public class AddressAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressDTO toDTO (Address address) {
		return modelMapper.map(address, AddressDTO.class);
	}
	
}
