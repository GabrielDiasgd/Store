package com.store.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.store.client.Client;
import com.store.client.repository.ClientRepository;
import com.store.exception.ClientNotFoundException;
import com.store.exception.EntityInUseException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client find (Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundException(clientId));
		
		return client;
	
	}
	
	
	public void delete (Long clientId) {
		try {
			clientRepository.deleteById(clientId);
		} catch (EmptyResultDataAccessException e) {
			throw new ClientNotFoundException(clientId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Code %d Client cannot be removed, because it is in use.", clientId));
		}
	}
}
