package com.autosync.autosync.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
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

    //storing the image ias BLOB : binary large object
    @Lob
    @Column(name = "car_image", columnDefinition = "BLOB")
    private byte[] carImage;
}
