package com.autosync.autosync.services;

import com.autosync.autosync.models.ProfileModel;
import com.autosync.autosync.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autosync.autosync.utils.CustomExceptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ProfileModel createProfile(ProfileModel profile){
        return profileRepository.save(profile);
    }

    public ProfileModel getProfileById(UUID profileId) throws CustomExceptions.ProfileNotFoundException{
        Optional<ProfileModel> profile = profileRepository.findById(profileId);
        if(profile.isPresent()){
            return profile.get();
        }
        else{
            throw new CustomExceptions.ProfileNotFoundException("Profile not found");
        }
    }

    public List<ProfileModel> getAllProfiles(){
        return profileRepository.findAll();
    }

    public ProfileModel updateProfile(UUID profileId,ProfileModel profileDetails) throws CustomExceptions.ProfileNotFoundException{
        Optional<ProfileModel> existingProfile = profileRepository.findById(profileId);
        if(existingProfile.isPresent()){
            existingProfile.get().setFirstName(profileDetails.getFirstName());
            existingProfile.get().setMiddleName(profileDetails.getMiddleName());
            existingProfile.get().setLastName(profileDetails.getLastName());
            existingProfile.get().setEmail(profileDetails.getEmail());
            existingProfile.get().setPhoneNumber(profileDetails.getPhoneNumber());
            existingProfile.get().setAddress(profileDetails.getAddress());

            return profileRepository.save(existingProfile.get());
        }
        else{
            throw new CustomExceptions.ProfileNotFoundException("Profile not found");
        }
    }



}
