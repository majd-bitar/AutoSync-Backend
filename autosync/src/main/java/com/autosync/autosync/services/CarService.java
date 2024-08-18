package com.autosync.autosync.services;

import com.autosync.autosync.models.CarModel;
import com.autosync.autosync.models.CarOwnerModel;
import com.autosync.autosync.repositories.CarOwnerRepository;
import com.autosync.autosync.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autosync.autosync.utils.CustomExceptions;

import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {


    @Autowired
    CarRepository carRepository;
    @Autowired
    CarOwnerRepository carOwnerRepository;

    public CarModel createCar(CarModel car) throws CustomExceptions.CarNotFoundException, CustomExceptions.CarOwnerNotProvidedException {
        if (car.getCarOwner() != null) {
            Optional<CarOwnerModel> carOwnerOptional = carOwnerRepository.findById(car.getCarOwner().getCarOwnerId());
            if (carOwnerOptional.isPresent()) {
                car.setCarOwner(carOwnerOptional.get());
                return carRepository.save(car);
            } else {
                throw new CustomExceptions.CarOwnerNotProvidedException("Car owner does not exist.");
            }
        } else {
            throw new CustomExceptions.CarOwnerNotProvidedException("Car owner is not provided.");
        }
    }

    public CarModel getCarById(UUID carId) throws CustomExceptions.CarNotFoundException{
        Optional<CarModel> retrievedCar = carRepository.findById(carId);
        if(retrievedCar.isPresent()){
            return retrievedCar.get();
        }
        else{
            throw new CustomExceptions.CarNotFoundException("Car not found");
        }
    }

    public CarModel updateCar(UUID carId,CarModel car) throws CustomExceptions.CarNotFoundException{
        Optional<CarModel> existingCar = carRepository.findById(carId);
        if(existingCar.isPresent()){
            existingCar.get().setModel(car.getModel());
            existingCar.get().setCarImage(car.getCarImage());
            existingCar.get().setYear(car.getYear());
        }
        else{
            throw new CustomExceptions.CarNotFoundException("Can't update a non existing car");
        }
    }
}
