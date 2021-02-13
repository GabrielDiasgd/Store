package com.store.profile.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileIdInput {
	
	@NotNull
	private Long id;

}
