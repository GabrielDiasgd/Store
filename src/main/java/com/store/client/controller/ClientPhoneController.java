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

import com.store.client.service.ClientService;
import com.store.phone.converters.PhoneAssemblerDTO;
import com.store.phone.converters.PhoneDisassemblerInput;
import com.store.phone.dto.PhoneDTO;
import com.store.phone.input.PhoneInput;
import com.store.phone.model.Phone;

@RestController
@RequestMapping("/clients/{clientId}/phones")
public class ClientPhoneController {
	
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PhoneAssemblerDTO phoneAssembler;
	
	@Autowired
	private PhoneDisassemblerInput phoneDisassembler;
	
	
	
	@PutMapping
	public PhoneDTO add (@PathVariable Long clientId, @RequestBody @Valid PhoneInput phoneInput) {
		Phone phone = phoneDisassembler.toDomainObject(phoneInput);
		return phoneAssembler.toDTO(clientService.addClientPhone(clientId, phone));
	}
	
	@PutMapping("/{phoneId}")
	public PhoneDTO updateClientPhone(@PathVariable Long clientId, 
			@PathVariable Long phoneId, @RequestBody @Valid PhoneInput phoneInput) {
		return phoneAssembler.toDTO(clientService.updateClientPhone(clientId, phoneId, phoneInput));
	}
	
	@DeleteMapping("/{phoneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClientPhone (@PathVariable Long clientId, @PathVariable Long phoneId) {
		clientService.deleteClientPhone(clientId, phoneId);
	}

}
