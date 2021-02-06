package com.store.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.exception.EntityInUseException;
import com.store.exception.PhoneNotFoundException;

@Service
public class PhoneService {

	@Autowired
	private PhoneRepository phoneRepository;

	public Phone find(Long phoneId) {
		return phoneRepository.findById(phoneId)
				.orElseThrow(() -> new PhoneNotFoundException(phoneId));
	}

	@Transactional
	public Phone save (Phone phone) {
		return phoneRepository.save(phone);
	}
	
	@Transactional
	public void delete (Long phoneId) {
		try {
			phoneRepository.deleteById(phoneId);
		} catch (EmptyResultDataAccessException e) {
			throw new PhoneNotFoundException(phoneId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Phone with id %d cannot be removed, beause it's in use.", phoneId));
	}
	}

}
