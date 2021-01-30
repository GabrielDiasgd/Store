package com.store.Product.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.store.Groups;
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
	
	@NotBlank(groups = Groups.registerProduct.class)
	private String name;
	
	private String description;
	
	@PositiveOrZero(groups = Groups.registerProduct.class)
	private Long stock;
	
	@PositiveOrZero(groups = Groups.registerProduct.class)
	private BigDecimal price;
	
	@PositiveOrZero(groups = Groups.registerProduct.class)
	private BigDecimal buyPrice;
	private Boolean active = true;
	
	@JsonIgnoreProperties(allowGetters = false)
	private Date dateLastSale;
	
	private Date dateLastPurchase;
	
	@CreationTimestamp
	private Date dateCreation;
	
	@UpdateTimestamp
	private Date dateUpdate;
	
	@Valid
	@NotNull(groups = Groups.registerProduct.class)
	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	
	

}
