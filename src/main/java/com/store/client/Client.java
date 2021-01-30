package com.store.client;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String cpf;
	private String rg;
	private String email;
	private String note;
	private Date birthDate;
	private Boolean active = true;
	private String clientType; //Enum
	private String clientStatus; //Enum
	private Date dateCreation;
	private Date dateUpdate;
	
	
}
