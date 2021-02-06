package com.store.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.address.Address;
import com.store.client.service.ClientService;
import com.store.exception.AddressNotFoundException;
import com.store.exception.BusinessException;
import com.store.exception.CityNotFoundException;

@RestController
@RequestMapping("/clients/{clientId}/address")
public class ClientAddressController {
	
	@Autowired
	private ClientService clientService;
	


	@PutMapping
	public Address add (@PathVariable Long clientId, @RequestBody Address address) {
		try {
			return clientService.addClientAddress(clientId, address);
		} catch (AddressNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
		
	}
	
	@PutMapping("/{addressId}")
	public Address update (@PathVariable Long clientId, @PathVariable Long addressId,
			@RequestBody Address address) {
		try {
			return clientService.updateClientAddress(clientId, addressId, address);
		} catch (AddressNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		

	}
	
	@DeleteMapping("/{addressId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociateClientAddress (@PathVariable Long clientId, @PathVariable Long addressId) {
		clientService.deleteClientAddress(clientId, addressId);
		
	}
	
	


}