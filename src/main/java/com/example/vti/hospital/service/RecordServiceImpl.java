package com.example.vti.hospital.service;

import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.Record;
import com.example.vti.hospital.repositories.DoctorRepository;
import com.example.vti.hospital.repositories.PatientRepository;
import com.example.vti.hospital.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Configurable
public class RecordServiceImpl implements RecordService{
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Override
    public List<Record> getAllRecord() {
        return  recordRepository.findAllRecordByIsDelete(false);
    }
    @Override
    public Record sreachIdRecord(String recordID){
        Optional<Record> optionalRecord = recordRepository.findByRecordID(recordID);
        Record record = optionalRecord.get();
        return record;
    }
    @Override
    public List<Record> getRecordByPaging(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Record> listRecords = recordRepository.findAllByIsDelete(false,pageable);
        return listRecords.stream().toList();
    }
    @Override
    public Record insertRecord(Record newRecord) {
        Patient patient =  patientRepository.findByPatientID(newRecord.getPatient().getPatientID()).orElse(null);
        if (null == patient) {
            patient = new Patient();
        }
        newRecord.setPatient(patient);
        Doctor doctor = (Doctor) doctorRepository.findByDoctorID(newRecord.getDoctor().getDoctorID()).orElse(null);
        if (null == doctor) {
            doctor = new Doctor();
        }
        newRecord.setDoctor(doctor);
        return recordRepository.save(newRecord);
    }

    @Override
    public Record deleteRecord(Long id) {
        Optional<Record> optionalRecord = recordRepository.findById(id);
        Record record = optionalRecord.get();
        record.setDelete(true);
        Record returRecord = recordRepository.save(record);
        return returRecord;
    }

    @Override
    public Record updateRecord(Long recordID,Record newRecords) {
        Optional<Record> optionalRecord = recordRepository.findById(recordID);
        optionalRecord.get().setRecordID(newRecords.getRecordID());
        optionalRecord.get().setIllnessContent(newRecords.getIllnessContent());
        optionalRecord.get().setConclusion(newRecords.getConclusion());
        optionalRecord.get().setDelete(newRecords.isDelete());
        recordRepository.save(optionalRecord.get());
        return optionalRecord.get();
    }
}
