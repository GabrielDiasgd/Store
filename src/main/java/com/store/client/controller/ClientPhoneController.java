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

import com.store.client.service.ClientService;
import com.store.phone.Phone;

@RestController
@RequestMapping("/clients/{clientId}/phones")
public class ClientPhoneController {
	
	
	@Autowired
	private ClientService clientService;
	
	
	@PutMapping
	public Phone add (@PathVariable Long clientId, @RequestBody Phone phone) {
		return clientService.addClientPhone(clientId, phone);
	}
	
	@PutMapping("/{phoneId}")
	public Phone updateClientPhone(@PathVariable Long clientId, 
			@PathVariable Long phoneId, @RequestBody Phone phone) {
		return clientService.updateClientPhone(clientId, phoneId, phone);
	}
	
	@DeleteMapping("/{phoneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClientPhone (@PathVariable Long clientId, @PathVariable Long phoneId) {
		clientService.deleteClientPhone(clientId, phoneId);
	}

}
