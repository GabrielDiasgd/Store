package com.store.provider.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.provider.converters.ProviderAssemblerDTO;
import com.store.provider.converters.ProviderDisassemblerInput;
import com.store.provider.dto.ProviderDTO;
import com.store.provider.input.ProviderInput;
import com.store.provider.model.Provider;
import com.store.provider.repository.ProviderRepository;
import com.store.provider.service.ProviderService;

@RestController
@RequestMapping("/providers")
public class ProviderController {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private ProviderAssemblerDTO providerAssembler;
	
	@Autowired
	private ProviderDisassemblerInput providerDisassembler;
	
	@GetMapping
	public List<ProviderDTO> listProviders () {
		return providerAssembler.toCollectionDTO(providerRepository.findAll());
	}
	
	
	@GetMapping("/{providerId}")
	public ProviderDTO find (@PathVariable Long providerId) {
		return providerAssembler.toDTO(providerService.find(providerId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProviderDTO add (@RequestBody @Valid ProviderInput providerInput) {
		Provider provider = providerDisassembler.toDomainObject(providerInput);
		return providerAssembler.toDTO(providerService.save(provider));
	}
	
	@PutMapping("/{providerId}")
	@ResponseStatus(HttpStatus.OK)
	public ProviderDTO update(@PathVariable Long providerId, @RequestBody @Valid ProviderInput providerInput) {
		Provider  provider = providerService.find(providerId);
		providerDisassembler.copyToDomainObject(providerInput, provider);
		
		return providerAssembler.toDTO(providerService.save(provider));
	}
	
	
	@DeleteMapping("/{providerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long providerId) {
		providerService.delete(providerId);
	}
	

}










