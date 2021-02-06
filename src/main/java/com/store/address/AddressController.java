package com.store.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Address add (@RequestBody Address address) {
		return addressService.save(address);
	}
}
