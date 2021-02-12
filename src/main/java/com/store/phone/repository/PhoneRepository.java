package com.store.phone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.phone.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
