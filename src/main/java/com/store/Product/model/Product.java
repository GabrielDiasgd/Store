package com.store.Product.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.store.provider.model.Provider;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Long stock;
	
	private BigDecimal price;
	
	
	private BigDecimal buyPrice;
	
	private Boolean active = true;
	

	private OffsetDateTime dateLastSale;
	
	private OffsetDateTime dateLastPurchase;
	
	@CreationTimestamp
	private OffsetDateTime dateCreation;
	
	@UpdateTimestamp
	private OffsetDateTime dateUpdate;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	
	

}
