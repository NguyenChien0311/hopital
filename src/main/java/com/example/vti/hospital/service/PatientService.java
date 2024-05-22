package com.example.vti.hospital.service;

import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface PatientService {
    List<Patient> getAllPatient();
    List<Patient> getPatientByPaging(int pageNumber, int pageSize);
    List<Patient> insertPatient(Patient newPatient);
    Patient patientDelete(Long id);
    Patient patientUpdate(Long patientID,Patient newPatient);


}
