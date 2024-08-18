package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileModel> getProfileById(@PathVariable UUID profileId) {
        ProfileModel profile = profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileModel>> getAllProfiles() {
        List<ProfileModel> profiles = profileService.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }


}
