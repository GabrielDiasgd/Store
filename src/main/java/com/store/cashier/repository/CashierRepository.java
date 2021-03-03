package com.store.cashier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.cashier.model.Cashier;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, Long> {
	
	@Query("from Cashier where status = 1")
	Optional<Cashier> findByStatusOpen ();

}
