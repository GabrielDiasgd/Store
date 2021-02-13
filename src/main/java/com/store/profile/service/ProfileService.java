package com.store.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.exception.EntityInUseException;
import com.store.exception.ProfileNotFoundException;
import com.store.profile.Profile;
import com.store.profile.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile find (Long profileId) {
		return profileRepository.findById(profileId)
				.orElseThrow(() -> new ProfileNotFoundException(profileId));
	}
	
	@Transactional
	public Profile save (Profile profile) {
		return profileRepository.save(profile);
	}
	
	@Transactional
	public void delete (Long profileId) {
		try {
			profileRepository.deleteById(profileId);
			profileRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProfileNotFoundException(profileId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d Profile cannot bee removed, because it is in use.", profileId));
		}
	}
}
