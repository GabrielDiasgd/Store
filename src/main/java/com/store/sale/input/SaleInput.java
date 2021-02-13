package com.store.sale.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.store.client.input.ClientIdInput;
import com.store.productsale.ProductSale;
import com.store.user.input.UserIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleInput {

	@Valid
	@NotNull
	private ClientIdInput client;
	
	@Valid
	@NotNull
	private UserIdInput user;
	
	
	private List<ProductSale> productsSale = new ArrayList<>();
	

}
