package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Services.ProfileService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileModel> updateProfile(@PathVariable UUID profileId, @RequestBody ProfileModel profileDetails) {
        ProfileModel updatedProfile = profileService.updateProfile(profileId, profileDetails);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Map<String, String>> deleteProfile(@PathVariable UUID profileId) {
        profileService.deleteProfile(profileId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Profile Deleted");

        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

}
