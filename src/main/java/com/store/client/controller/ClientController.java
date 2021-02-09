package com.store.client.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.client.Client;
import com.store.client.repository.ClientRepository;
import com.store.client.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	

	@GetMapping
	public List<Client> listClients () {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public Client find (@PathVariable Long clientId) {
		return clientService.find(clientId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client add (@RequestBody Client client) {
		return clientService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public Client update (@PathVariable Long clientId, @RequestBody Client client) {
	
		Client currentClient = clientService.find(clientId);
		
		BeanUtils.copyProperties(client, currentClient, "id", "dateCreation", "clientAddress", "clientPhone");
		
		return clientService.save(currentClient);
	}

}








