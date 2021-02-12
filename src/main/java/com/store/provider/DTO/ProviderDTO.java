package com.store.provider.dto;

import com.store.address.DTO.AddressDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDTO {

	private Long id;
	private String name;
	private String cnpj;
	private String email;
	private Boolean active = true;
	private String phone;
	private String cellPhone;
	private String sac;
	private String site;
	private String contact;
	private String note;
	
	private AddressDTO address;
}
