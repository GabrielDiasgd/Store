package com.store.address;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.store.city.City;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {


	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String street;
	private Integer number;
	private String neighborhood;
	private String complement;
	private String cep;
	

	@CreationTimestamp
	private LocalDateTime dateCreation;
	
	@UpdateTimestamp
	private LocalDateTime dateUpdate;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private City city;
	
	
}
