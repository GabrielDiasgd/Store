package com.store.client.input;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.store.client.model.ClientStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInput {

	@NotEmpty
	private String name;
	
	private String cpf;
	private String rg;
	
	@Email
	private String email;
	private String note;
	
	private LocalDate birthDate;
	private Boolean active = true;
	private String clientType; //Enum
	
	private ClientStatus clientStatus; //Enum
	
}
