package com.example.vti.hospital.repositories;


import com.example.vti.hospital.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   // List<User> findByUserName(String userName);

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Page<User> findAllByIsActive(boolean b, Pageable pageable);

    List<User> findAllUserByIsActive (boolean b);
}
