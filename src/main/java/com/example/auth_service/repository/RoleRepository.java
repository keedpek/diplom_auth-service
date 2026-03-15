package com.example.auth_service.repository;

import com.example.auth_service.entity.Role;
import com.example.auth_service.enums.RoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
  Optional<Role> findByName(RoleTypes name);
}
