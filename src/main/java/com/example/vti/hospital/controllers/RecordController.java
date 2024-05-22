package com.example.vti.hospital.controllers;

import com.example.vti.hospital.dto.DoctorDto;
import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.models.Record;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.RecordRepository;
import com.example.vti.hospital.service.RecordService;
import com.example.vti.hospital.service.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/api/v1/record")
public class RecordController {
    @Autowired
    RecordServiceImpl recordService;
    @Autowired
    RecordRepository recordRepository;
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllRecord(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success", recordService.getAllRecord())
        );
    }

    @GetMapping("/getBookingByPaging")
    ResponseEntity<ResponseObject> getRecordByPaging(@RequestParam int pageNumber, int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success", recordService.getRecordByPaging( pageNumber,pageSize))
        );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertRecord(@RequestBody Record record){
        Optional<Record> foundRecord = recordRepository.findByRecordID(record.getRecordID());

        if (foundRecord.isPresent())
        {
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200,"Record already exist", "" ));
        }
        return  ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"success", recordService.insertRecord(record)));
    }

    @GetMapping("/delete/{recordID}")
    ResponseEntity<ResponseObject> deleteRecord(@PathVariable Long recordID){
        Optional<Record> optionalRecord = recordRepository.findById(recordID);
        if(optionalRecord.isPresent()) {
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Success", recordService.deleteRecord(recordID))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(200,"Fail", ""));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateDoctor(@PathVariable(name = "id") Long id,@RequestBody Record record) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Success", recordService.updateRecord(id,record))
        );
    }
    @GetMapping("/{recordID}")
    ResponseEntity<ResponseObject> sreachIdRecord(@PathVariable String recordID){
        Optional<Record> optionalRecord = recordRepository.findByRecordID(recordID);
        if(optionalRecord.isPresent()) {
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Success", recordService.sreachIdRecord(recordID))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(200,"Record not Found", ""));
    }
}
