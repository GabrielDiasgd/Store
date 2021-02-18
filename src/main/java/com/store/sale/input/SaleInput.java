package com.store.sale.input;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.store.client.input.ClientIdInput;
import com.store.productsale.ProductSale;
import com.store.sale.model.TypeSale;
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
	private Long discountPercentage;
	private BigDecimal discountValue;
	private BigDecimal amountPaid;
	private TypeSale typeSale; 
	
	
	private List<ProductSale> productsSale = new ArrayList<>();
	

}
