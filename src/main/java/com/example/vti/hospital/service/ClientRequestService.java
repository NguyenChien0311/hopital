package com.example.vti.hospital.service;

import com.example.vti.hospital.models.CLientRequest;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.ClientRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class ClientRequestService {
    @Autowired
    ClientRequestRepository clientRequestRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    public CLientRequest insertClient(CLientRequest cLientRequest){
        cLientRequest.setPatientList (new ArrayList<>());
        cLientRequest.setId(sequenceGeneratorService.generateSequence(cLientRequest.SEQUENCE_NAME));
        return clientRequestRepository.save(cLientRequest);
    }
    // INSERT PatientList
    public CLientRequest insertPatient(Patient patient, Long RequestID)
    {
        Optional<CLientRequest> optionalCLientRequest = clientRequestRepository.findById(RequestID);
        if(optionalCLientRequest.isPresent()){
            CLientRequest cLientRequest = optionalCLientRequest.get();
            List<Patient> patientList = cLientRequest.getPatientList();
            patient.setId(sequenceGeneratorService.generateSequence(patient.SEQUENCE_NAME));

            boolean dupplicate = false;
            for(Patient patient1: patientList)
            {
                if(patient.getPatientID().equals(patient1.getPatientID()))
                {
                    dupplicate = true;
                }
            }
            if(dupplicate== false){
                patientList.add(patient);
            }
            cLientRequest.setPatientList(patientList);


            return clientRequestRepository.save(cLientRequest);
        }
        return null;
    }
}
