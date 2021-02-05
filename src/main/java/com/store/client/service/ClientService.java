package com.store.client.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.address.Address;
import com.store.address.AddressRepository;
import com.store.address.AddressService;
import com.store.client.Client;
import com.store.client.repository.ClientRepository;
import com.store.exception.ClientNotFoundException;
import com.store.exception.EntityInUseException;
import com.store.phone.Phone;
import com.store.phone.PhoneRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Transactional
	public Client save(Client client) {
		client.getClientAddress().forEach(clientAddress -> {
			Address address = clientAddress;
			addressRepository.save(address);
			
			
		});

		return clientRepository.save(client);
	}

	public void delete(Long clientId) {
		try {
			clientRepository.deleteById(clientId);
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

	@Transactional
	public void disassociateClientAddress(Long clientId, Long addressId) {
		Client client = find(clientId);
		Address currentAddress = addressService.find(addressId);

		client.disassociateAddress(currentAddress);
	}

	@Transactional
	public Address associateClientAddress(Long clientId, Address address) {
		Client client = find(clientId);
		addressRepository.save(address);
		client.associateAddress(address);

		return address;
	}

	@Transactional
	public Address updateClientAddress(Long clientId, Long addressId, Address address) {
		Client client = find(clientId);
		Address currentAddress = addressService.find(addressId);
		
			client.getClientAddress().forEach(ads -> {
				System.out.println(ads.getId() + " = " + currentAddress.getId());
				if (ads.equals(currentAddress)) {
					BeanUtils.copyProperties(address, currentAddress, "id", "dateCreation");
					} 	
			});
		
			return addressRepository.save(currentAddress);
		}

	@Transactional
	public Phone associateClientPhone (Long clientId, Phone phone) {
		Client client = find(clientId);
		phoneRepository.save(phone);
		 client.getClientPhone().add(phone);
		 
		 return phone;
	
	
	}

}
