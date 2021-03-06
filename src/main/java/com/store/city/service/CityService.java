package com.store.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.city.model.City;
import com.store.city.repository.CityRepository;
import com.store.exception.CityNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	
	public City find (Long cityId) {
		return cityRepository.findById(cityId)
				.orElseThrow(() -> new CityNotFoundException(cityId));
	}

}
