package com.store.client.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.address.Address;
import com.store.address.AddressService;
import com.store.client.model.Client;
import com.store.client.repository.ClientRepository;
import com.store.exception.ClientNotFoundException;
import com.store.exception.EntityInUseException;
import com.store.phone.Phone;
import com.store.phone.PhoneService;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PhoneService phoneService;

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

	//Client Address
	
	@Transactional
	public Address addClientAddress(Long clientId, Address address) {
		Client client = find(clientId);
		Address currentAddress = addressService.save(address);
		client.associateAddress(currentAddress);
		return address;
	}

	@Transactional
	public Address updateClientAddress(Long clientId, Long addressId, Address address) {
		Client client = find(clientId);
		Address currentAddress = addressService.find(addressId);
		
			client.getClientAddress().forEach(ads -> {
				System.out.println(ads.getId() + " = " + currentAddress.getId());
				if (ads.equals(currentAddress)) {
					 BeanUtils.copyProperties(address, currentAddress, "id", "dateCreation"); //Tentar o select conjunto para ver se resolve
					} 	
			});
		
			return addressService.save(currentAddress);
		}
	
	@Transactional
	public void deleteClientAddress(Long clientId, Long addressId) {
		Client client = find(clientId);
		Address currentAddress = addressService.find(addressId); //ao passar um id que não está vinculado ao cliente da pau
		addressService.delete(addressId);
		
		client.disassociateAddress(currentAddress);
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
	public Phone updateClientPhone (Long clientId, Long phoneId, Phone phone) {
		Client client = find(clientId);
		Phone currentPhone = phoneService.find(phoneId);
		
		
		client.getClientPhone().forEach(clientPhone -> {
			if (clientPhone.equals(currentPhone)) {
				BeanUtils.copyProperties(phone, currentPhone, "id", "dateCreation");
			}
		});
		
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
