package com.store.client.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.client.input.ClientInput;
import com.store.client.model.Client;

@Component
public class ClientDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Client toDomainObject (ClientInput clientInput) {
		return modelMapper.map(clientInput, Client.class);
	}

}
