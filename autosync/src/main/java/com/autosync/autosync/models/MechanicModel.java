package com.autosync.autosync.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "mechanics")
public class MechanicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mechanic_id",unique = true,nullable = false)
    private UUID mechanicId;
}
