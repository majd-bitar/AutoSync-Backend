package com.autosync.autosync.Services;

import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ProfileModel createProfile(ProfileModel profile) throws CustomExceptions.UniqueKeyViolationException {
        try {
            return profileRepository.save(profile);
        } catch (DataIntegrityViolationException e) {
            throw new CustomExceptions.UniqueKeyViolationException("Profile already exists");
        }
    }

    public ProfileModel getProfileById(UUID profileId) throws CustomExceptions.ProfileNotFoundException {
        Optional<ProfileModel> profile = profileRepository.findById(profileId);
        if (profile.isPresent()) {
            return profile.get();
        } else {
            throw new CustomExceptions.ProfileNotFoundException("Profile not found");
        }
    }


    public List<ProfileModel> getAllProfiles() {
        try {
            return profileRepository.findAll();
        } catch (Exception e) {
            throw new CustomExceptions.ProfileNotFoundException("Error retrieving profiles");
        }
    }

    public ProfileModel updateProfile(UUID profileId, ProfileModel profileDetails) throws CustomExceptions.ProfileNotFoundException {
        try {
            Optional<ProfileModel> existingProfileOptional = profileRepository.findById(profileId);

            if (existingProfileOptional.isPresent()) {
                ProfileModel existingProfile = existingProfileOptional.get();

                // Update attributes only if they are not null in profileDetails
                if (profileDetails.getFirstName() != null) {
                    existingProfile.setFirstName(profileDetails.getFirstName());
                }
                if (profileDetails.getMiddleName() != null) {
                    existingProfile.setMiddleName(profileDetails.getMiddleName());
                }
                if (profileDetails.getLastName() != null) {
                    existingProfile.setLastName(profileDetails.getLastName());
                }
                if (profileDetails.getEmail() != null) {
                    existingProfile.setEmail(profileDetails.getEmail());
                }
                if (profileDetails.getPhoneNumber() != null) {
                    existingProfile.setPhoneNumber(profileDetails.getPhoneNumber());
                }
                if (profileDetails.getAddress() != null) {
                    existingProfile.setAddress(profileDetails.getAddress());
                }
                return profileRepository.save(existingProfile);
            } else {
                throw new CustomExceptions.ProfileNotFoundException("Profile not found");
            }
        } catch (Exception e) {
            throw new CustomExceptions.ProfileNotFoundException("Profile not found");
        }
    }

    public void deleteProfile(UUID profileId) throws CustomExceptions.ProfileNotFoundException {
        try {
            Optional<ProfileModel> existingProfile = profileRepository.findById(profileId);
            if (existingProfile.isPresent()) {
                profileRepository.delete(existingProfile.get());
            } else {
                throw new CustomExceptions.ProfileNotFoundException("Profile not found");
            }
        } catch (Exception e) {
            throw new CustomExceptions.ProfileNotFoundException("Profile not found");
        }
    }
}
