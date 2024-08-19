package com.autosync.autosync.Services;


import com.autosync.autosync.ExceptionHandling.CustomExceptions;
import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Models.LicenseModel;
import com.autosync.autosync.Models.MechanicModel;
import com.autosync.autosync.Repositories.CompanyRepository;
import com.autosync.autosync.Repositories.LicenseRepository;
import com.autosync.autosync.Repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<CompanyModel> getAllCompanies() {
        return companyRepository.findAll();
    }

    public CompanyModel updateCompany(UUID companyId, CompanyModel companyDetails) throws CustomExceptions.CompanyNotFoundException {
        Optional<CompanyModel> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            CompanyModel existingCompany = companyOptional.get();

            if(existingCompany.getCompanyAddress()!=null)
                existingCompany.setCompanyAddress(companyDetails.getCompanyAddress());
            if(existingCompany.getCompanyLogo()!=null)
                existingCompany.setCompanyLogo(companyDetails.getCompanyLogo());
            if(existingCompany.getCompanyName()!=null)
                existingCompany.setCompanyName(companyDetails.getCompanyName());
            if(existingCompany.getCompanyPhoneNumber()!=null)
                existingCompany.setCompanyPhoneNumber(companyDetails.getCompanyPhoneNumber());
            return companyRepository.save(existingCompany);
        } else {
            throw new CustomExceptions.CompanyNotFoundException("Company not found.");
        }
    }

    public void deleteCompany(UUID companyId) throws CustomExceptions.CompanyNotFoundException {
        Optional<CompanyModel> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            companyRepository.delete(companyOptional.get());
        } else {
            throw new CustomExceptions.CompanyNotFoundException("Company not found.");
        }
    }

    public CompanyModel assignMechanicToCompany(UUID companyId, UUID mechanicId) throws CustomExceptions.CompanyNotFoundException, CustomExceptions.MechanicNotFoundException {
        CompanyModel company = getCompanyById(companyId);
        Optional<MechanicModel> mechanicOptional = mechanicRepository.findById(mechanicId);
        if (mechanicOptional.isPresent()) {
            MechanicModel mechanic = mechanicOptional.get();
            mechanic.setCompany(company);
            mechanicRepository.save(mechanic);
            return company;
        } else {
            throw new CustomExceptions.MechanicNotFoundException("Mechanic not found.");
        }
    }

}
