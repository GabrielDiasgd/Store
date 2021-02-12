package com.store.city.DTO;

import javax.validation.constraints.NotEmpty;

import com.store.state.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {
	
	private Long id;

	@NotEmpty
	private String name;
	
	private State state;


}
