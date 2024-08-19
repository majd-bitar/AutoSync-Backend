package com.autosync.autosync.Services;


import com.autosync.autosync.ExceptionHandling.CustomExceptions;
import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Repositories.CompanyRepository;
import com.autosync.autosync.Repositories.LicenseRepository;
import com.autosync.autosync.Repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    public CompanyModel createCompany(CompanyModel company) {
        return companyRepository.save(company);
    }

    public CompanyModel getCompanyById(UUID companyId) throws CustomExceptions.CompanyNotFoundException {
        Optional<CompanyModel> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            return companyOptional.get();
        } else {
            throw new CustomExceptions.CompanyNotFoundException("Company not found.");
        }
    }

}
