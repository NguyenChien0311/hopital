package com.example.vti.hospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Transient
    public static final String SEQUENCE_NAME = "patient_senquence";
    @Id
    private Long id;
    private Long userID;

    private String patientID;
    private boolean isDelete;

//    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private List<Record> records;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private PatientInfo patientInfo;
//    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private List<Booking> bookings;




}
