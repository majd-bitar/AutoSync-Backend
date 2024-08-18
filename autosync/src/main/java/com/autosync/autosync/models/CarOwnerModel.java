package com.autosync.autosync.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "car_owners")
public class CarOwnerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_owner_id",nullable = false,unique = true)
    private UUID carOwnerId;

    //a car owner has a profile
    @OneToOne
    @JoinColumn(name = "profile_id")
    private ProfileModel carOwnerProfile;

    //a car owner belongs to a mechanic
    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private MechanicModel carOwnerMechanic;

    @OneToOne
    @JoinColumn(name = "car_id")
    private CarModel car;

}
