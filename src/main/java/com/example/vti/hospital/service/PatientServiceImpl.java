package com.example.vti.hospital.service;

import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Configurable
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatient(){
        List<Patient> patientList = patientRepository.findAllPatientByIsDelete(false);
        return patientList;
    }
    @Override
    public List<Patient> getPatientByPaging(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Patient> listPatient = patientRepository.findAllByIsDelete(false,pageable);
        return listPatient.stream().toList();
    }
    @Override
    public List<Patient> insertPatient(Patient newPatient){
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patientRepository.save(newPatient));
        return patientList;
    }
    @Override
    public Patient patientDelete(Long id){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        Patient patient = optionalPatient.get();
        patient.setDelete(true);
        Patient returnPatient = patientRepository.save(patient);
        return returnPatient;
    }
    @Override
    public Patient patientUpdate(Long patientID,Patient newPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        newPatient.getPatientInfo().setId(patientID);
        optionalPatient.get().setPatientID(newPatient.getPatientID());
        optionalPatient.get().setPatientInfo(newPatient.getPatientInfo());
        optionalPatient.get().setUserID(newPatient.getUserID());
        optionalPatient.get().setDelete(newPatient.isDelete());
        patientRepository.save(optionalPatient.get());
        return optionalPatient.get();

    }
    }
