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
import com.store.exception.EntityNotFoundException;

@RestController
@RequestMapping("/clients/{clientId}/address")
public class ClientAddressController {
	
	@Autowired
	private ClientService clientService;
	
	

	@PutMapping
	public Address add (@PathVariable Long clientId, @RequestBody Address address) {
		return clientService.associateClientAddress(clientId, address);
	}
	
	@PutMapping("/{addressId}")
	public Address update (@PathVariable Long clientId, @PathVariable Long addressId,
			@RequestBody Address address) {
		
		try {
			return clientService.updateClientAddress(clientId, addressId, address);
		} catch (EntityNotFoundException e) {
			throw new AddressNotFoundException(String.format(
					"Não foi encontrado endereço de código %d, para o cliente de código %d.", addressId, clientId));
		}
		

	}
	
	@DeleteMapping("/{addressId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociateClientAddress (@PathVariable Long clientId, @PathVariable Long addressId) {
		clientService.disassociateClientAddress(clientId, addressId);
		
	}
	
	


}
