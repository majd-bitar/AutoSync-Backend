package com.autosync.autosync.Services;

import com.autosync.autosync.Models.CarModel;
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

    public CarOwnerModel createCarOwner(CarOwnerModel carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    public CarOwnerModel getCarOwnerById(UUID carOwnerId) throws CustomExceptions.CarOwnerNotFoundException {
        Optional<CarOwnerModel> retrievedCarOwner = carOwnerRepository.findById(carOwnerId);
        if (retrievedCarOwner.isPresent()) {
            return retrievedCarOwner.get();
        } else {
            throw new CustomExceptions.CarOwnerNotFoundException("Car owner not found.");
        }
    }

    public List<CarOwnerModel> getAllCarOwners() {
        return carOwnerRepository.findAll();
    }

    public void deleteCarOwner(UUID carOwnerId) throws CustomExceptions.CarOwnerNotFoundException {
        Optional<CarOwnerModel> existingCarOwner = carOwnerRepository.findById(carOwnerId);
        if (existingCarOwner.isPresent()) {
            carOwnerRepository.delete(existingCarOwner.get());
        } else {
            throw new CustomExceptions.CarOwnerNotFoundException("Car owner not found.");
        }
    }

    // Associate a car owner with a car
    public CarOwnerModel associateCarWithOwner(UUID carOwnerId, CarModel car) throws CustomExceptions.CarOwnerNotFoundException {
        CarOwnerModel carOwner = getCarOwnerById(carOwnerId);
        carOwner.setCar(car); // Assuming addCar method exists in CarOwnerModel
        return carOwnerRepository.save(carOwner);
    }
}