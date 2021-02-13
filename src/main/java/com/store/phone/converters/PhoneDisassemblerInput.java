package com.store.phone.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.phone.input.PhoneInput;
import com.store.phone.model.Phone;

@Component
public class PhoneDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Phone toDomainObject (PhoneInput phoneInput) {
		return modelMapper.map(phoneInput, Phone.class);
	}
	
	
	public void copyToDomainObject (PhoneInput phoneInput, Phone phone) {
		modelMapper.map(phoneInput, phone);
	}

}
