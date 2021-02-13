package com.store.permission.input;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionInput {

	@NotEmpty
	private String description;
	
}
