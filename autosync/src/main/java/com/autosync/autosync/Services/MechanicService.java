package com.autosync.autosync.Services;

import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Models.MechanicModel;
import com.autosync.autosync.Repositories.CompanyRepository;
import com.autosync.autosync.Repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;

import java.util.Optional;

@Service
public class MechanicService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MechanicRepository mechanicRepository;

    public MechanicModel createMechanic(MechanicModel mechanic) throws CustomExceptions.MechanicNotProvidedException {
        if (mechanic.getCompany() != null) {
            Optional<CompanyModel> companyOptional = companyRepository.findById(mechanic.getCompany().getCompanyId());
            if (companyOptional.isPresent()) {
                mechanic.setCompany(companyOptional.get());
                return mechanicRepository.save(mechanic);
            } else {
                throw new CustomExceptions.CompanyNotFoundException("Company does not exist.");
            }
        } else {
            throw new CustomExceptions.MechanicNotProvidedException("Company not provided for the mechanic.");
        }
    }
}
