package com.store.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.exception.EntityInUseException;
import com.store.exception.PermissionNotFoundException;
import com.store.permission.Permission;
import com.store.permission.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Transactional
	public Permission save (Permission permission) {
		return permissionRepository.save(permission);
	}
	
	public Permission find (Long permissionId) {
		return permissionRepository.findById(permissionId)
				.orElseThrow(() ->  new PermissionNotFoundException(permissionId));
	}
	
	@Transactional
	public void delete (Long permissionId) {
		try {
			permissionRepository.deleteById(permissionId);
			permissionRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PermissionNotFoundException(permissionId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d Permission cannot be removed, because it is in use.", permissionId));
		}
	}
}
