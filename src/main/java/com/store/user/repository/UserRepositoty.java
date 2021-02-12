package com.store.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.user.model.User;

@Repository
public interface UserRepositoty extends JpaRepository<User, Long> {

}
