package com.store.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return clientService.associateClientPhone(clientId, phone);
	}

}
