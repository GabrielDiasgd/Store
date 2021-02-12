package com.store.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.address.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
