package com.store.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.brand.model.Brand;
import com.store.brand.repository.BrandRepository;
import com.store.exception.BrandNotFoundException;
import com.store.exception.BusinessException;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;

	@Transactional
	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}
	
	public Brand find (Long brandId) {
		return brandRepository.findById(brandId)
				.orElseThrow(() -> new BrandNotFoundException(brandId));
	}
	
	@Transactional
	public void delete (Long brandId) {
		try {
			brandRepository.deleteById(brandId);
			brandRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new BrandNotFoundException(brandId);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException(String.format("Brand with id %d cannot be removed, because it's in use.", brandId));
	}
	
	}
}
