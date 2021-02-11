package com.store.client.dto;

import java.time.LocalDate;

import com.store.client.model.ClientStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
	
	private Long id;
	private String name;
	private String cpf;
	private String rg;
	private String email;
	private String note;
	private LocalDate birthDate;
	private Boolean active = true;
	private String clientType; //Enum
	private ClientStatus clientStatus; //Enum
}
