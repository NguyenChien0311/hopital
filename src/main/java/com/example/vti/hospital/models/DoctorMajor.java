package com.example.vti.hospital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class DoctorMajor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String majorName;

    @ManyToMany(mappedBy = "likedDoctor")
    @JsonIgnore
    Set<Doctor> likes;
    public DoctorMajor() {
    }

    public Set<Doctor> getLikes() {
        return likes;
    }

    public void setLikes(Set<Doctor> likes) {
        this.likes = likes;
    }

    public DoctorMajor(String majorName) {
        this.majorName = majorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
