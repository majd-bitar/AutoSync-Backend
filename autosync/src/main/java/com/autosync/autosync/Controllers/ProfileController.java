package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileModel> createProfile(@RequestBody ProfileModel profile){
        ProfileModel savedProfile = profileService.createProfile(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

}
