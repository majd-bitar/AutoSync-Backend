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

}
