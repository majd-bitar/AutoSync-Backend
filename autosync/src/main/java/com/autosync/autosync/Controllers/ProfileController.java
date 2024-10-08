package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
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

    @PreAuthorize("hasRole('CAR_OWNER')")
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
