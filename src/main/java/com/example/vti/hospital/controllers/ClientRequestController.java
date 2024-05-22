package com.example.vti.hospital.controllers;

import com.example.vti.hospital.models.CLientRequest;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.ClientRequestRepository;
import com.example.vti.hospital.service.ClientRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping(path  = "/api/v1/ClientRequestController")
public class ClientRequestController {

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @Autowired
    private ClientRequestService ClientRequestService;

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertClient(@RequestBody CLientRequest newCLientRequest) {

        Optional<CLientRequest> foundTopic = clientRequestRepository.findById(newCLientRequest.getId());

        if (foundTopic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200, "Topics already exist", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200, "SUCCESS", ClientRequestService.insertClient(newCLientRequest)));
    }
    @PostMapping("/insertPatient")
    ResponseEntity<ResponseObject> insertPatientMongo(@RequestBody Patient newPatient, @RequestParam Long RequestID) {
        Optional<CLientRequest> optionalCLientRequest = clientRequestRepository.findByClientRequest(newPatient.getPatientID());
        if(optionalCLientRequest.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(new ResponseObject(200, "Patient EXIST", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200, "SUCCESS", ClientRequestService.insertPatient(newPatient, RequestID)));
    }
}
