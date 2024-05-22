package com.example.vti.hospital.controllers;


import com.example.vti.hospital.models.Booking;
import com.example.vti.hospital.models.ResponseObject;
import com.example.vti.hospital.repositories.BookingRepository;
import com.example.vti.hospital.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/booking")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllBooking(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success", bookingService.getAllBooking())
        );
    }
    @GetMapping("/getByPaging")
    ResponseEntity<ResponseObject> getBookingByPaging(@RequestParam int pageNumber, int pageSize)
    {
        List<Booking> bookingList = bookingService.getBookingByPaging(pageNumber,pageSize);
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"",bookingList));
    }
    @GetMapping("/getBookingID/{bookingID}")
    ResponseEntity<ResponseObject> getByBookingID(@PathVariable String bookingID){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Success", bookingService.getBookingByBookingID(bookingID))
        );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertBooking(@RequestBody Booking newBooking) {

        Optional<Booking> foundBooking = bookingRepository.findByBookingID(newBooking.getBookingID());

        if (foundBooking.isPresent())
        {
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200,"Booking already exist", "" ));
        }
        return  ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(200,"success", bookingService.addBooking(newBooking) ));
    }

    @GetMapping("/delete/{bookingID}")
    ResponseEntity<ResponseObject> deleteBooking(@PathVariable Long bookingID){
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);
        if(optionalBooking.isPresent()) {
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Success", bookingService.deleteBooking(bookingID))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(200,"Booking not Found", ""));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateBooking(@PathVariable(name = "id") Long id,@RequestBody Booking newBooking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
                    body(new ResponseObject(200,"Success" ,bookingService.updateBooking(id,newBooking)));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ResponseObject(200,"Patient does not exist",""));

    }
}
