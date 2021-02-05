package com.store.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.permission.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
