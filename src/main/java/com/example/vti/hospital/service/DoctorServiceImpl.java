package com.example.vti.hospital.service;

import com.example.vti.hospital.dto.DoctorDto;
import com.example.vti.hospital.models.Doctor;
import com.example.vti.hospital.models.DoctorMajor;
import com.example.vti.hospital.models.Patient;
import com.example.vti.hospital.models.Record;
import com.example.vti.hospital.repositories.DoctorMajorRepository;
import com.example.vti.hospital.repositories.DoctorRepository;
import com.example.vti.hospital.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Configurable
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorMajorRepository doctorMajorRepository;
    @Autowired
    RecordRepository recordRepository;
    @Override
    public List<Doctor> getDoctorByPaging(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Doctor> listBooking = doctorRepository.findAllByIsDelete(false, pageable);
        return listBooking.stream().toList();
    }

    @Override
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        mapDtoToEntity(doctorDto, doctor);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return mapEntityToDto(savedDoctor);
    }

    @Override
    public List<DoctorDto> getAllDoctor(){
        List<DoctorDto> doctorDtos = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.findAllDoctorByIsDelete(false);
        doctors.stream().forEach(doctor -> {
            DoctorDto doctorDto = mapEntityToDto(doctor);
            doctorDtos.add(doctorDto);
        });
        return doctorDtos;
    }

    @Override
    public DoctorDto updateDoctor(Long doctorID, DoctorDto doctor) {
        Optional<Doctor> dtr = doctorRepository.findById(doctorID);
        dtr.get().getLikedDoctor().clear();
        mapDtoToEntity(doctor, dtr.get());
        Doctor doc = doctorRepository.save(dtr.get());
        return mapEntityToDto(doc);
    }

    @Override
    public Doctor deleteDoctor(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor = optionalDoctor.get();
        doctor.setDelete(true);
        Doctor returnDoctor = doctorRepository.save(doctor);
        return returnDoctor;
    }

    private void mapDtoToEntity(DoctorDto doctorDto, Doctor doctor) {

        doctor.setDoctorID(doctorDto.getDoctorID());
        doctor.setUserID(doctorDto.getUserID());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setName(doctorDto.getName());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setGender(doctorDto.isGender());
        doctor.setDelete(doctorDto.isDelete());
        if (null == doctor.getLikedDoctor()) {
            doctor.setLikedDoctor(new HashSet<>());
        }
        doctorDto.getDoctorMajor().stream().forEach(majorName -> {
            DoctorMajor doctorMajor = doctorMajorRepository.findByMajorName(majorName);
            if (null == doctorMajor) {
                doctorMajor = new DoctorMajor();
                doctorMajor.setLikes(new HashSet<>());
            }
            doctorMajor.setMajorName(majorName);
            doctor.addDoctorMajor(doctorMajor);
        });


    }
    private DoctorDto mapEntityToDto(Doctor doctor) {
        DoctorDto responseDto = new DoctorDto();
        responseDto.setId(doctor.getId());
        responseDto.setName(doctor.getName());
        responseDto.setPhone(doctor.getPhone());
        responseDto.setEmail(doctor.getEmail());
        responseDto.setAddress(doctor.getAddress());
        responseDto.setGender(doctor.isGender());
        responseDto.setDelete(doctor.isDelete());
        responseDto.setUserID(doctor.getUserID());
        responseDto.setDoctorID(doctor.getDoctorID());
        responseDto.setDoctorMajor(doctor.getLikedDoctor().stream().map(DoctorMajor::getMajorName).collect(Collectors.toSet()));
        return responseDto;
    }


}


