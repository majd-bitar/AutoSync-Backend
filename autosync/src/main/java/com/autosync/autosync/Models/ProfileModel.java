package com.autosync.autosync.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "profiles")
public class ProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,name = "profile_id",nullable = false)
    private UUID profileId;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "middle_name",nullable = false)
    private String middleName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "phone_number",unique = true,nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    //a profile has a login
    @OneToOne(mappedBy = "loginProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private LoginModel login;

    //a profile belongs to a car owner
    @OneToOne(mappedBy = "carOwnerProfile",cascade = CascadeType.ALL,orphanRemoval = true)
    private CarOwnerModel carOwner;

    //a profile belongs to a mechanic
    @OneToOne(mappedBy = "mechanicProfile",cascade = CascadeType.ALL,orphanRemoval = true)
    private MechanicModel mechanic;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
