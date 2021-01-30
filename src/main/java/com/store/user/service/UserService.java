package com.store.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.store.exception.EntityInUseException;
import com.store.exception.UserNotFoundException;
import com.store.user.User;
import com.store.user.repository.UserRepositoty;

@Service
public class UserService {

	@Autowired
	private UserRepositoty userRepositoty;
	
	public User find (Long userId) {
		User user = userRepositoty.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		return user;
	}
	
	
	public void delete(Long userId) {
		try {
			userRepositoty.deleteById(userId);
		}catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d User cannot be removed, because it is in use.", userId));
	}
	}
}
