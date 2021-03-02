package com.store.formpayment.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormPaymentIdInput {
	
	@NotNull
	private Long id;

}
