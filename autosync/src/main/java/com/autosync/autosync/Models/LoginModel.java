package com.autosync.autosync.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "logins")
public class LoginModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login_id",nullable = false,unique = true)
    private UUID loginId;

    @Column(name = "username",unique = true,nullable = false)
    private String username;

    @Column(name = "password",unique = true,nullable = false)
    private String password;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    //a login has a profile
    @OneToOne()
    @JoinColumn(name = "profile_id")
    private ProfileModel loginProfile;

}

enum Role{
    CAR_OWNER,MECHANIC,ADMIN
}

enum Status{
    ACTIVE,INACTIVE,SUSPENDED
}