package com.store.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.profile.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
