package com.store.provider.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.store.provider.ProviderService;
import com.store.provider.model.Provider;
import com.store.provider.repository.ProviderRepository;

@RestController
@RequestMapping("/providers")
public class ProviderController {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private ProviderService providerService;
	
	@GetMapping
	public List<Provider> listProvider () {
		return providerRepository.findAll();
	}
	
	
	@GetMapping("/{providerId}")
	public Provider find (@PathVariable Long providerId) {
		return providerService.find(providerId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Provider add (@RequestBody Provider provider) {
		return providerService.save(provider);
	}
	
	@PutMapping("/{providerId}")
	@ResponseStatus(HttpStatus.OK)
	public Provider update(@PathVariable Long providerId, @RequestBody Provider provider) {
		Provider  currentProvider = providerService.find(providerId);
		BeanUtils.copyProperties(provider, currentProvider , "id", "dateCreation");
		
		return providerService.save(currentProvider);
	}
	
	
	@DeleteMapping("/{providerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long providerId) {
		providerService.delete(providerId);
	}
	

}










