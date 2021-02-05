package com.store.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.exception.AddressNotFoundException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository  addressRepository;
	
	
	public Address find (Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException(addressId));	}
}
