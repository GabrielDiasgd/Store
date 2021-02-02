package com.store.profile.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
