package com.example.vti.hospital.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CLientRequest {
    @Transient
    public static final String SEQUENCE_NAME = "ClientRequest_sequence";
    @Id
    private  Long id;

    private String fullName;
    private String clientRequest;
    int numberPhone;
    private Date birthDay;
    private boolean gender;

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    private List<Patient> patientList;
    private boolean isDelete;



}
