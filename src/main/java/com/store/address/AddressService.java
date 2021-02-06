package com.store.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.city.City;
import com.store.city.CityService;
import com.store.exception.AddressNotFoundException;
import com.store.exception.EntityInUseException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired 
	private CityService cityService;

	public Address find(Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException(addressId));
	}

	@Transactional
	public Address save(Address address) {
		City city = cityService.find(address.getCity().getId());
		address.setCity(city);
		
		return addressRepository.save(address);
	}
	
	
	public void delete (Long addressId) {
		try {
			addressRepository.deleteById(addressId);
		} catch (EmptyResultDataAccessException e) {
			throw new AddressNotFoundException(addressId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Address with id %d cannot be removed, because it is in use.", addressId));
		}
	}

}
