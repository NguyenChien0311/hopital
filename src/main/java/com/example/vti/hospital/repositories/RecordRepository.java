package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record,Long> {

    Page<Record> findAllByIsDelete(boolean b, Pageable pageable);

    Optional<Record> findByRecordID(String recordID);

    List<Record> findByDoctorId(Long doctorId);

    List<Record> findAllRecordByIsDelete(boolean b);
}
