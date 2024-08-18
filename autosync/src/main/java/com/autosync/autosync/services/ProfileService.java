package com.autosync.autosync.services;

import com.autosync.autosync.models.ProfileModel;
import com.autosync.autosync.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ProfileModel createProfile(ProfileModel profile){
        return profileRepository.save(profile);
    }

}
