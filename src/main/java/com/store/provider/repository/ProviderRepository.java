package com.store.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.provider.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
