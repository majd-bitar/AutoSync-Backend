package com.autosync.autosync.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cars")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id",unique = true,nullable = false)
    private UUID carId;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Year year;

    //storing the image as BLOB : binary large object
    @Lob
    @Column(name = "car_image", columnDefinition = "BYTEA")
    private byte[] carImage;

    //a car can be owned by many car owners
    @OneToOne(mappedBy = "car")
    private CarOwnerModel carOwner;

    //a car can have many diagnostics
    @OneToMany(mappedBy = "carDiagnostic")
    private List<DiagnosticModel> diagnostics;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
