package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Page<Doctor> findAllByIsDelete(boolean isDelete, Pageable pageable);
    Optional<Doctor> findByEmail(String email);

    List<Doctor> findAllDoctorByIsDelete(boolean b);

    Optional<Object> findByDoctorID(String doctorID);
}
