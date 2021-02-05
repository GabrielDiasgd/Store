package com.store.provider.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.store.address.Address;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Provider {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Address address;
	
	@CreationTimestamp
	private Date dateCreation;
	
	@UpdateTimestamp
	private Date dateUpdate;

}
