package com.store.user.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdInput {

	@NotNull
	private Long id;
}
