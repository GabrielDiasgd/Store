package com.store.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.client.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
