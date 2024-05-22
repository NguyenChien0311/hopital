package com.example.vti.hospital.repositories;

import com.example.vti.hospital.models.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByBookingID(String bookingID);

    Page<Booking> findAllByIsDelete(boolean b, Pageable pageable);

    List<Booking> findAllBookingByIsDelete(boolean b);
}
