package com.autosync.autosync.Services;

import com.autosync.autosync.Models.CarOwnerModel;
import com.autosync.autosync.Repositories.CarOwnerRepository;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarOwnerService {

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    // Create a new car owner
    public CarOwnerModel createCarOwner(CarOwnerModel carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    // Retrieve a car owner by ID
    public CarOwnerModel getCarOwnerById(UUID carOwnerId) throws CustomExceptions.CarOwnerNotFoundException {
        Optional<CarOwnerModel> retrievedCarOwner = carOwnerRepository.findById(carOwnerId);
        if (retrievedCarOwner.isPresent()) {
            return retrievedCarOwner.get();
        } else {
            throw new CustomExceptions.CarOwnerNotFoundException("Car owner not found.");
        }
    }

    // Retrieve all car owners
    public List<CarOwnerModel> getAllCarOwners() {
        return carOwnerRepository.findAll();
    }

    // Delete a car owner
    public void deleteCarOwner(UUID carOwnerId) throws CustomExceptions.CarOwnerNotFoundException {
        Optional<CarOwnerModel> existingCarOwner = carOwnerRepository.findById(carOwnerId);
        if (existingCarOwner.isPresent()) {
            carOwnerRepository.delete(existingCarOwner.get());
        } else {
            throw new CustomExceptions.CarOwnerNotFoundException("Car owner not found.");
        }
    }
}