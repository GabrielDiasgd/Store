package com.store.phone.input;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneInput {

	@NotEmpty
	private String number;

}
