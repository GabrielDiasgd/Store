package com.store.formpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.formpayment.model.FormPayment;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {

}
