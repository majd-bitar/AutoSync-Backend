package com.autosync.autosync.Services;

import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Models.MechanicModel;
import com.autosync.autosync.Repositories.CompanyRepository;
import com.autosync.autosync.Repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MechanicService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MechanicRepository mechanicRepository;

    public MechanicModel createMechanic(MechanicModel mechanic) throws CustomExceptions.MechanicNotProvidedException, CustomExceptions.CompanyNotProvidedException {
        if (mechanic == null) {
            throw new CustomExceptions.MechanicNotProvidedException("Mechanic is not provided.");
        }

        if (mechanic.getCompany() != null) {
            Optional<CompanyModel> companyOptional = companyRepository.findById(mechanic.getCompany().getCompanyId());
            if (companyOptional.isPresent()) {
                mechanic.setCompany(companyOptional.get());
            } else {
                throw new CustomExceptions.CompanyNotProvidedException("Company does not exist.");
            }
        } else {
            throw new CustomExceptions.CompanyNotProvidedException("Company is not provided.");
        }

        return mechanicRepository.save(mechanic);
    }

    public MechanicModel getMechanicById(UUID mechanicId) throws CustomExceptions.MechanicNotFoundException {
        Optional<MechanicModel> retrievedMechanic = mechanicRepository.findById(mechanicId);
        if (retrievedMechanic.isPresent()) {
            return retrievedMechanic.get();
        } else {
            throw new CustomExceptions.MechanicNotFoundException("Mechanic not found");
        }
    }

    public List<MechanicModel> getAllMechanics() {
        try {
            return mechanicRepository.findAll();
        } catch (Exception e) {
            throw new CustomExceptions.MechanicNotFoundException("Error retrieving mechanics");
        }
    }

}
