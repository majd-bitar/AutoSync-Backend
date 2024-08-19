package com.autosync.autosync.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "licenses")
public class LicenseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "license_id",unique = true,nullable = false)
    private UUID licenseId;

    @Column(name = "max_clients")
    private Integer maxClients;

    @Column(name = "price")
    private Float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private Type type;

    //a license type can be owned by one company
    @OneToOne(mappedBy = "license")
    private CompanyModel company;

    public enum Type{
        SILVER,GOLD,PLATINUM,DIAMOND
    }
}


