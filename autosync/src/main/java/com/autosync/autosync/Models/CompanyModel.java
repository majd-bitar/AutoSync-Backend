package com.autosync.autosync.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "companies")
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id",unique = true,nullable = false)
    private UUID companyId;

    @Column(name = "company_name",nullable = false)
    private String companyName;

    //storing the image as BLOB : binary large object
    @Lob
    @Column(name = "company_logo", columnDefinition = "BYTEA")
    private byte[] companyLogo;

    @Column(name = "company_address",nullable = false)
    private String companyAddress;

    @Column(name = "company_phone_number",nullable = false)
    private String companyPhoneNumber;

    //a company can have many mechanics
    @OneToMany(mappedBy = "company")
    private List<MechanicModel> mechanics;

    //a company can only have one license type
    @OneToOne
    @JoinColumn(name = "license_id")
    private LicenseModel license;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
