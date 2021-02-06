package com.store.provider;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.store.address.Address;
import com.store.address.AddressService;
import com.store.exception.EntityInUseException;
import com.store.exception.ProviderNotFoundException;
import com.store.provider.model.Provider;
import com.store.provider.repository.ProviderRepository;

@Service
public class ProviderService {

	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private AddressService addressService;
	
	
	public Provider find (Long providerId) {
		Provider provider = providerRepository.findById(providerId)
				.orElseThrow(() -> new ProviderNotFoundException(providerId));
		return provider;
	}
	
	@Transactional
	public Provider save (Provider provider) {
		Address address = addressService.save(provider.getAddress());
		provider.setAddress(address);
		
		return providerRepository.save(provider);
	}
	
	@Transactional
	public void delete (Long providerId) {
		try {
			providerRepository.deleteById(providerId);
		} catch (EmptyResultDataAccessException e) {
			throw new ProviderNotFoundException (providerId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d Provider cannot be removed, because it is in use.", providerId));
		}
	}
}
