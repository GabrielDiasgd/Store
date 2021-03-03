package com.store.cashier.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.store.sale.model.Sale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cashier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private OffsetDateTime closingDate;
	
	@CreationTimestamp
	private OffsetDateTime openingDate;
	private StatusCashier status;
	private BigDecimal totalValue;
	private BigDecimal valueReported;
	private BigDecimal cardsValue;
	private BigDecimal moneyValue;
	
	@OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL)
	private List<Sale> salesCashier = new ArrayList<>();
	
	
	public void openCashier() {
		this.status = StatusCashier.OPEN;
	}
	
	public void closeCashier() {
		this.status = StatusCashier.CLOSE;
		this.closingDate = OffsetDateTime.now();
	}
}
