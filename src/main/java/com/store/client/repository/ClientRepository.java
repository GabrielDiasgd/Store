package com.store.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.client.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	

//	@Query("from Client where address.id = :address and id = :client ")
//	Optional<Client> findById1(@Param("client")Long clientId,@Param("address") Long addressId);
	
	 @Query("from Client c left join fetch c.clientAddress")
	 List<Client> findAll();


}
