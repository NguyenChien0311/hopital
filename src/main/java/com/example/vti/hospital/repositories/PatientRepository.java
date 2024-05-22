package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends MongoRepository<Patient, Long> {
    Page<Patient> findAllByIsDelete(boolean isDelete, Pageable pageable);

    List<Patient> findAllPatientByIsDelete(boolean b);

    Optional<Patient> findByPatientID(String patientID);
}
