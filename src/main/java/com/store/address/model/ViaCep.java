package com.store.address.model;

import lombok.Data;

@Data
//@Entity
public class ViaCep {
	
	private String cep;
	private String logradouro;
	private String complemto;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;

}
