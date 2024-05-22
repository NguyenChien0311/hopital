package com.example.vti.hospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String doctorID;
    private Long userID;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean gender;
    private boolean isDelete;


    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "doctor_major_liked",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "doctormajor_id"))
    Set<DoctorMajor> likedDoctor;
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Record> records;
    public void addDoctorMajor(DoctorMajor doctorMajor) {
        this.likedDoctor.add(doctorMajor);
        doctorMajor.getLikes().add(this);
    }
    public void removeDoctorMajor(DoctorMajor doctorMajor) {
        this.getLikedDoctor().remove(doctorMajor);
        doctorMajor.getLikes().remove(this);
    }
    public void removeDoctorMajors() {
        for (DoctorMajor doctorMajor : new HashSet<>(likedDoctor)) {
            removeDoctorMajor(doctorMajor);
        }
    }




}
