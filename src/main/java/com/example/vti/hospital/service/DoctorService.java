package com.example.vti.hospital.service;

import com.example.vti.hospital.dto.DoctorDto;
import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface DoctorService {
    List<Doctor> getDoctorByPaging(int pageNumber, int pageSize);
    public DoctorDto addDoctor(DoctorDto doctorDto);
    public List<DoctorDto> getAllDoctor();
    public DoctorDto updateDoctor(Long doctorID, DoctorDto doctor);
    public Doctor deleteDoctor(Long id);

}
