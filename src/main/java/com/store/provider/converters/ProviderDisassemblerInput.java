package com.store.provider.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.provider.input.ProviderInput;
import com.store.provider.model.Provider;

@Component
public class ProviderDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Provider toDomainObject(ProviderInput providerInput) {
		return modelMapper.map(providerInput, Provider.class);
	}
	
	public void copyToDomainObject (ProviderInput providerInput, Provider provider) {
		modelMapper.map(providerInput, provider);
	}

}
