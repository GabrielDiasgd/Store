package com.store.profile.input;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileInput {
	
	@NotEmpty
	private String description;

}
