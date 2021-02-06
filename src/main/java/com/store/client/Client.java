package com.store.client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.store.address.Address;
import com.store.phone.Phone;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	
	private String cpf;
	private String rg;
	private String email;
	private String note;
	private Date birthDate;
	private Boolean active = true;
	private String clientType; //Enum
	private String clientStatus; //Enum
	
	@CreationTimestamp
	private LocalDateTime dateCreation;
	
	@UpdateTimestamp
	private LocalDateTime dateUpdate;
	
	@ManyToMany
	@JoinTable(name = "client_address", 
			joinColumns = @JoinColumn(name = "client_id"),
			inverseJoinColumns = @JoinColumn(name = "address_id"))
	private List<Address> clientAddress = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "client_phone",
			joinColumns =  @JoinColumn(name = "client_id"),
			inverseJoinColumns = @JoinColumn(name = "phone_id"))
	private List<Phone> clientPhone = new ArrayList<>();
	
	
	public boolean disassociateAddress(Address address) {
		return getClientAddress().remove(address);
	}
	
	public boolean associateAddress(Address address) {
		return getClientAddress().add(address);
	}
	
	public Boolean disassociatePhone(Phone phone) {
		return getClientPhone().remove(phone);
	}
	
	public Boolean assiciatePhone(Phone phone) {
		return getClientPhone().add(phone);
	}
	
}
