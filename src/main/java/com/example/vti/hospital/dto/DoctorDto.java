package com.example.vti.hospital.dto;

import com.example.vti.hospital.models.Record;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public class DoctorDto {
    private Long id;
    private boolean gender;
    private boolean isDelete;
    private String doctorID;
    private Long userID;
    private String name;
    private String address;
    private String phone;
    private String email;

    private List<Record> record;
    private Set<String> doctorMajor = new HashSet<>();
}
