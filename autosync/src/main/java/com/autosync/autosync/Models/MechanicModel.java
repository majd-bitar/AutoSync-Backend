package com.autosync.autosync.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    //a mechanic has one profile
    @OneToOne
    @JoinColumn(name="profile_id")
    private ProfileModel mechanicProfile;


    //a mechanic has many car owners
    @OneToMany(mappedBy = "carOwnerMechanic",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarOwnerModel> carOwners;

    //a mechanic belongs to a company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;


}
