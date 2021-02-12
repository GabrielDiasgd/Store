package com.store.client.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.client.converters.ClientAssemblerDTO;
import com.store.client.converters.ClientDisassemblerInput;
import com.store.client.dto.ClientDTO;
import com.store.client.input.ClientInput;
import com.store.client.model.Client;
import com.store.client.repository.ClientRepository;
import com.store.client.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientAssemblerDTO clientAssembler;
	
	@Autowired
	private ClientDisassemblerInput clientDisassembler;
	

	@GetMapping
	public List<ClientDTO> listClients () {
		return clientAssembler.toCollectionDTO(clientRepository.findAll());
	}
	
	@GetMapping("/{clientId}")
	public ClientDTO find (@PathVariable Long clientId) {
		return clientAssembler.toDTO(clientService.find(clientId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDTO add (@RequestBody @Valid ClientInput clientInput) {
	
		Client client = clientService.save(clientDisassembler.toDomainObject(clientInput));
		
		return clientAssembler.toDTO(client);
	}
	
	@PutMapping("/{clientId}")
	public ClientDTO update (@PathVariable Long clientId, @RequestBody @Valid ClientInput clientInput) {
	
		Client currentClient = clientService.find(clientId);
		Client client = clientDisassembler.toDomainObject(clientInput);
		
		BeanUtils.copyProperties(client, currentClient, "id", "dateCreation", "clientAddress", "clientPhone");
		
		return clientAssembler.toDTO(clientService.save(currentClient));
	}
	
	@DeleteMapping("/{clientId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long clientId) {
		clientService.delete(clientId);
	}

}








