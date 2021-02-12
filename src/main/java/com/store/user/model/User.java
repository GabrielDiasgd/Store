package com.store.user.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.store.profile.Profile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	private String email;
	private String cpf;
	private String rg;
	private String password;
	private Boolean active = true;
	
	@CreationTimestamp
	private OffsetDateTime dateCreation;
	
	@UpdateTimestamp
	private OffsetDateTime dateUpdate;
	
	@ManyToOne
	@JoinColumn(name = "profile_id", nullable = false)
	private Profile profile;
}
