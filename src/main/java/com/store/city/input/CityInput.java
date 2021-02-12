package com.store.city.input;

import javax.validation.constraints.NotEmpty;

import com.store.state.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInput {

	@NotEmpty
	private String name;
	
	private State state;

}
