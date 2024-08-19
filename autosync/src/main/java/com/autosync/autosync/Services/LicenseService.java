package com.autosync.autosync.Services;
import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Models.LicenseModel;
import com.autosync.autosync.Repositories.CompanyRepository;
import com.autosync.autosync.Repositories.LicenseRepository;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    LicenseRepository licenseRepository;

    @Autowired
    CompanyRepository companyRepository;

    public LicenseModel createLicense(LicenseModel licenseModel){
        return licenseRepository.save(licenseModel);
    }
    public LicenseModel getLicenseById(UUID licenseId) throws CustomExceptions.LicenseNotFoundException {
        Optional<LicenseModel> retrievedLicense = licenseRepository.findById(licenseId);
        if(retrievedLicense.isPresent()){
            return retrievedLicense.get();
        } else {
            throw new CustomExceptions.LicenseNotFoundException("License not found");
        }
    }
    public List<LicenseModel> getAllLicenses() {
        return licenseRepository.findAll();
    }

    public LicenseModel updateLicense(UUID licenseId, LicenseModel licenseDetails) throws CustomExceptions.LicenseNotFoundException {
        Optional<LicenseModel> existingLicenseOptional = licenseRepository.findById(licenseId);
        if(existingLicenseOptional.isPresent()) {
            LicenseModel existingLicense = existingLicenseOptional.get();
            if (licenseDetails.getType() != null) {
                existingLicense.setType(licenseDetails.getType());
            }
            if (licenseDetails.getMaxClients() != null) {
                existingLicense.setMaxClients(licenseDetails.getMaxClients());
            }
            if (licenseDetails.getPrice() != null) {
                existingLicense.setPrice(licenseDetails.getPrice());
            }
            return licenseRepository.save(existingLicense);
        } else {
            throw new CustomExceptions.LicenseNotFoundException("License not found");
        }
    }

    public void deleteLicense(UUID licenseId) throws CustomExceptions.LicenseNotFoundException {
        Optional<LicenseModel> existingLicense = licenseRepository.findById(licenseId);
        if(existingLicense.isPresent()){
            licenseRepository.delete(existingLicense.get());
        } else {
            throw new CustomExceptions.LicenseNotFoundException("License not found");
        }
    }

}
