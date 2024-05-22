package com.example.vti.hospital.service;

import com.example.vti.hospital.models.Booking;
import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.Record;
import com.example.vti.hospital.repositories.BookingRepository;
import com.example.vti.hospital.repositories.PatientRepository;
import com.example.vti.hospital.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PatientRepository patientRepository;
    public List<Booking> getAllBooking() {
        return bookingRepository.findAllBookingByIsDelete(false);
    }
    public List<Booking> getBookingByPaging(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Booking> listBooking = bookingRepository.findAllByIsDelete(false, pageable);
        return listBooking.stream().toList();
    }
    public Booking getBookingByBookingID(String bookingID)
    {
        Optional<Booking> booking = bookingRepository.findByBookingID(bookingID);
        if(booking.isPresent()){
            return booking.get();
        }
        return null;
    }

    public Booking addBooking(Booking booking) {
        Patient patient = patientRepository.findByPatientID(booking.getPatient().getPatientID()).orElse(null);
        if (null == patient) {
            patient = new Patient();
        }
        booking.setPatient(patient);
        return bookingRepository.save(booking);
    }

    public Booking deleteBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        Booking booking = optionalBooking.get();
        booking.setDelete(true);
        Booking returnBooking = bookingRepository.save(booking);
        return returnBooking;
    }


    public Booking updateBooking(Long bookingID,Booking newBooking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);
        optionalBooking.get().setBookingID(newBooking.getBookingID());
        optionalBooking.get().setBookDate(newBooking.getBookDate());
        optionalBooking.get().setContent(newBooking.getContent());
        optionalBooking.get().setDelete(newBooking.isDelete());
        bookingRepository.save(optionalBooking.get());
        return optionalBooking.get();
    }

   
}
