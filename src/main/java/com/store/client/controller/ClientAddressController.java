package com.store.client.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.address.DTO.AddressDTO;
import com.store.address.converters.AddressAssemblerDTO;
import com.store.address.converters.AddressDisassemblerInput;
import com.store.address.input.AddressInput;
import com.store.address.model.Address;
import com.store.client.service.ClientService;
import com.store.exception.AddressNotFoundException;
import com.store.exception.BusinessException;
import com.store.exception.CityNotFoundException;

@RestController
@RequestMapping("/clients/{clientId}/address")
public class ClientAddressController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AddressAssemblerDTO addressAssembler;
	
	@Autowired
	private AddressDisassemblerInput addressDisassembler;

	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AddressDTO add (@PathVariable Long clientId, @RequestBody @Valid AddressInput addressInput) {
		try {
			Address address = addressDisassembler.toDomainObjec(addressInput);
			return addressAssembler.toDTO(clientService.addClientAddress(clientId, address));
		} catch (AddressNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
		
	}
	
	@PutMapping("/{addressId}")
	public AddressDTO update (@PathVariable Long clientId, @PathVariable Long addressId,
			@RequestBody @Valid AddressInput addressInput) {
		try {
			return addressAssembler.toDTO(clientService.updateClientAddress(clientId, addressId, addressInput));
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
