package com.store.installmentsale.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.installmentsale.model.InstallmentSale;

@Repository
public interface InstallmentSaleRepository extends JpaRepository<InstallmentSale, Long> {

}
