package com.example.vti.hospital.controllers;

import com.example.vti.hospital.dto.DoctorDto;
import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.DoctorRepository;
import com.example.vti.hospital.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/v1/doctor")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorServiceImpl doctorService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllDoctor(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success", doctorService.getAllDoctor())
        );
    }
    @GetMapping("/getDoctorByPaging")
    ResponseEntity<ResponseObject> getBookingByPaging(@RequestParam int pageNumber, int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success", doctorService.getDoctorByPaging( pageNumber,pageSize))
        );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertDoctor(@RequestBody DoctorDto doctorDto){
        Optional<Doctor> foundDoctor = doctorRepository.findByEmail(doctorDto.getEmail());

        if (foundDoctor.isPresent())
        {
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200,"Doctor already exist", "" ));
        }
        return  ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"success", doctorService.addDoctor(doctorDto)));
    }

    @GetMapping("/delete/{doctorID}")
    ResponseEntity<ResponseObject> deleteDoctor(@PathVariable Long doctorID){
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorID);
        if(optionalDoctor.isPresent()) {
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Success", doctorService.deleteDoctor(doctorID))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(200,"Doctor not Found", ""));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateDoctor(@PathVariable(name = "id") Long id,@RequestBody DoctorDto doctor) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Success", doctorService.updateDoctor(id,doctor))
        );
    }
}
