package com.example.vti.hospital.controllers;

import com.example.vti.hospital.models.CLientRequest;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.PatientRepository;
import com.example.vti.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;


    @GetMapping("/getByPaging")
    ResponseEntity<ResponseObject> getPatientByPaging(@RequestParam int pageNumber, int pageSize)
    {
        List<Patient> patients = patientService.getPatientByPaging(pageNumber,pageSize);
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"",patients));
    }

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllPatient()
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success",patientService.getAllPatient())
        );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertPatient(@RequestBody Patient newPatient)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Insert Patient successfully", patientService.insertPatient(newPatient))
        );
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updatePatient(@PathVariable(name = "id") Long id,@RequestBody Patient newPatient){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200,"Success" ,patientService.patientUpdate(id,newPatient)));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ResponseObject(200,"Patient does not exist",""));

    }

    @GetMapping("/delete/{patientID}")
    ResponseEntity<ResponseObject> deletePatient(@PathVariable Long patientID){
        Optional<Patient> optionalPatient = patientRepository.findById(patientID);
        if(optionalPatient.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseObject(200,"Success" ,patientService.patientDelete(patientID)));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ResponseObject(200,"Fail",""));
    }



}
