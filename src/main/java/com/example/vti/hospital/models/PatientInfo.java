package com.example.vti.hospital.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PatientInfo {

    @Transient
    public static final String SEQUENCE_NAME = "patientinfo_senquence";
    @Id
    private Long id;
    private String email;
    private String name;
    private int age;
    private String phone;
    private String address;
    private boolean gender;

//    @OneToOne(mappedBy = "patientInfo")
//    @JsonIgnore

}
