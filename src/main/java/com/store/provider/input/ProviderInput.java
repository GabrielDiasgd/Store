package com.store.provider.input;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.store.address.input.AddressInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderInput {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String cnpj;
	private String email;
	private Boolean active = true;
	private String phone;
	private String cellPhone;
	private String sac;
	private String site;
	private String contact;
	private String note;

	@Valid
	@NotNull
	private AddressInput address;

}
