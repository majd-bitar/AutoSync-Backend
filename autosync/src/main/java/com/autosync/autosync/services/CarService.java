package com.autosync.autosync.services;

import com.autosync.autosync.models.CarModel;
import com.autosync.autosync.models.CarOwnerModel;
import com.autosync.autosync.repositories.CarOwnerRepository;
import com.autosync.autosync.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autosync.autosync.utils.CustomExceptions;

import java.util.Optional;

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
}
