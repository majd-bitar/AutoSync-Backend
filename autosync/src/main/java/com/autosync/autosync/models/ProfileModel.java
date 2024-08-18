package com.autosync.autosync.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
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

    @Column(name = "email",unique = true,nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

}
