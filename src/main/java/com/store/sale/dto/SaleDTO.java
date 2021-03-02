package com.store.sale.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.store.client.dto.ClientSaleDTO;
import com.store.formpayment.dto.FormPaymentDTO;
import com.store.sale.model.StatusSale;
import com.store.user.dto.UserSaleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDTO {
	
	private Long id;
	private String codeSale; 
	private BigDecimal subtotal; 
	private BigDecimal totalValue;
	private Long discountPercentage;
	private BigDecimal discountValue;
	private BigDecimal amountPaid;
	private OffsetDateTime dateSale;
	private StatusSale statusSale;
	
	private FormPaymentDTO formPayment;
	private ClientSaleDTO client;
	private UserSaleDTO user;

}
