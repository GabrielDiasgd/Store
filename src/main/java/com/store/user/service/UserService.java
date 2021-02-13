package com.store.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.exception.EntityInUseException;
import com.store.exception.UserNotFoundException;
import com.store.profile.Profile;
import com.store.profile.service.ProfileService;
import com.store.user.model.User;
import com.store.user.repository.UserRepositoty;

@Service
public class UserService {

	@Autowired
	private UserRepositoty userRepositoty;
	
	@Autowired
	private ProfileService profileService;
	
	public User find (Long userId) {
		User user = userRepositoty.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		return user;
	}
	
	@Transactional
	public User save (User user) {
		Profile profile = profileService.find(user.getProfile().getId());
		
		user.setProfile(profile);
		return userRepositoty.save(user);
	}
	
	
	@Transactional
	public void delete(Long userId) {
		try {
			userRepositoty.deleteById(userId);
			userRepositoty.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d User cannot be removed, because it is in use.", userId));
	}
	}
}
