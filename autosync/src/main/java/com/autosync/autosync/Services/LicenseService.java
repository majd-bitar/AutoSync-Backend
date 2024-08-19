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
}
