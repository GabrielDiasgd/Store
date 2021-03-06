package com.store.city.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.store.state.State;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class City {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private State state;
}
