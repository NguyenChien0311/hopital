package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.DoctorMajor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorMajorRepository extends JpaRepository<DoctorMajor,Long> {
    public DoctorMajor findByMajorName(String majorName);
}
