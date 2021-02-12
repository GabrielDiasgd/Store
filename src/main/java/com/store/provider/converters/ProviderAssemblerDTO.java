package com.store.provider.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.provider.dto.ProviderDTO;
import com.store.provider.model.Provider;

@Component
public class ProviderAssemblerDTO {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProviderDTO toDTO (Provider provider) {
		return modelMapper.map(provider, ProviderDTO.class);
	}
	
	
	public List<ProviderDTO> toCollectionDTO (List<Provider> providers) {
		return providers.stream()
				.map(provider -> toDTO(provider))
				.collect(Collectors.toList());
	}

}
