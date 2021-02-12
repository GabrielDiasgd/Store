package com.store.user.dto;

import com.store.profile.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String rg;
	private Boolean active = true;

	private Profile profile;
}
