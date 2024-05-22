package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.ERole;
import com.example.vti.hospital.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
