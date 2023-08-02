package com.codyMang.chitchatmonolith.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codyMang.chitchatmonolith.model.ERole;
import com.codyMang.chitchatmonolith.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    List<Role> findAll();
}