package com.store.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.address.Service.AddressService;
import com.store.address.converters.AddressDisassemblerInput;
import com.store.address.input.AddressInput;
import com.store.address.model.Address;
import com.store.client.model.Client;
import com.store.client.repository.ClientRepository;
import com.store.exception.AddressNotFoundException;
import com.store.exception.ClientNotFoundException;
import com.store.exception.EntityInUseException;
import com.store.exception.PhoneNotFoundException;
import com.store.phone.converters.PhoneDisassemblerInput;
import com.store.phone.input.PhoneInput;
import com.store.phone.model.Phone;
import com.store.phone.service.PhoneService;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private AddressDisassemblerInput addressDisassembler;
	
	@Autowired
	private PhoneDisassemblerInput phoneDisassembler;

	@Transactional
	public Client save(Client client) {
		client.getClientAddress().forEach(clientAddress -> {
			Address address = clientAddress;
			addressService.save(address);
		});
		
		client.getClientPhone().forEach(clientPhone -> {
			phoneService.save(clientPhone);
		});

		return clientRepository.save(client);
	}

	@Transactional
	public void delete(Long clientId) {
		try {
			clientRepository.deleteById(clientId);
			clientRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ClientNotFoundException(clientId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Code %d Client cannot be removed, because it is in use.", clientId));
		}
	}

	public Client find(Long clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundException(clientId));

		return client;
	}

	//Client Address
	
	@Transactional
	public Address addClientAddress(Long clientId, Address address) {
		Client client = find(clientId);
		Address currentAddress = addressService.save(address);
		client.associateAddress(currentAddress);
		return address;
	}

	@Transactional
	public Address updateClientAddress(Long clientId, Long addressId, AddressInput addressInput) {
		Client client = find(clientId);
		Address currentAddress = addressService.find(addressId);
		
			boolean existAddress = client.getClientAddress().stream()
					.anyMatch((a) -> a.equals(currentAddress));
			
			if (existAddress) {
				 addressDisassembler.copyToDomainObject(addressInput, currentAddress);
			}else {
				throw new AddressNotFoundException(String.format("Not exist address with id %d, in client with id %d", addressId, clientId));
			}
	
			return addressService.save(currentAddress);
		}

	
	@Transactional
	public void deleteClientAddress(Long clientId, Long addressId) {
		Client client = find(clientId);
		Address currentAddress = addressService.find(addressId); //ao passar um id que não está vinculado ao cliente da pau
		

		boolean existAddress = client.getClientAddress().stream()
				.anyMatch((a) -> a.equals(currentAddress));
		
		if (existAddress) {
			client.disassociateAddress(currentAddress);
			addressService.delete(addressId);
		}else {
			throw new AddressNotFoundException(String.format("Not exist address with id %d, in client with id %d", addressId, clientId));
		}
		
	}

	
	//Client Phone

	@Transactional
	public Phone addClientPhone (Long clientId, Phone phone) {
		Client client = find(clientId);
		phoneService.save(phone);
		 client.getClientPhone().add(phone);
		 
		 return phone;

	}

	@Transactional
	public Phone updateClientPhone (Long clientId, Long phoneId, PhoneInput phoneInput) {
		Client client = find(clientId);
		Phone currentPhone = phoneService.find(phoneId);
		
		
		boolean existPhone = client.getClientPhone().stream()
				.anyMatch((phone) -> phone.equals(currentPhone));
		
		if (existPhone) {
			phoneDisassembler.copyToDomainObject(phoneInput, currentPhone);
		} else {
			throw new PhoneNotFoundException(String.format("Not exist phone with id %d, in client %s", phoneId, client.getName()));
		}
		
		return phoneService.save(currentPhone);

	}

	@Transactional
	public void deleteClientPhone(Long clientId, Long phoneId) {
		Client client = find(clientId);
		Phone phone = phoneService.find(phoneId);
		 client.disassociatePhone(phone);
		 phoneService.delete(phoneId);
		 
	}
	
	
	
	
	
}
