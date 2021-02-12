package com.store.phone.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.phone.dto.PhoneDTO;
import com.store.phone.model.Phone;

@Component
public class PhoneAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PhoneDTO toDTO (Phone phone) {
		return modelMapper.map(phone, PhoneDTO.class);
	}
	
	public List<PhoneDTO> toCollectionDTO (List<Phone> phones){
		return phones.stream()
				.map(phone -> toDTO(phone))
				.collect(Collectors.toList());
	}

}
