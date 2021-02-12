package com.store.user.input;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.store.profile.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {
	
	@NotEmpty
	private String name;
	private String email;
	private String cpf;
	private String rg;
	
	@NotEmpty
	@Min(value = 8)
	private String password;
	private Boolean active = true;
	
	@Valid
	@NotNull
	private Profile profile;

}
