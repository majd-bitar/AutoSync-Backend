package com.autosync.autosync.Services;

import com.autosync.autosync.Models.CarModel;
import com.autosync.autosync.Models.CarOwnerModel;
import com.autosync.autosync.Repositories.CarOwnerRepository;
import com.autosync.autosync.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;

import java.util.List;
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

    public List<CarModel> getAllCars(){
        try {
            return carRepository.findAll();
        } catch (Exception e) {
            throw new CustomExceptions.CarNotFoundException("Error retrieving cars");
        }
    }

    public CarModel updateCar(UUID carId, CarModel carDetails) throws CustomExceptions.CarNotFoundException {
        try {
            Optional<CarModel> existingCarOptional = carRepository.findById(carId);

            if (existingCarOptional.isPresent()) {
                CarModel existingCar = existingCarOptional.get();
                if (carDetails.getModel() != null) {
                    existingCar.setModel(carDetails.getModel());
                }
                if (carDetails.getCarImage() != null) {
                    existingCar.setCarImage(carDetails.getCarImage());
                }
                if (carDetails.getYear() != null) {
                    existingCar.setYear(carDetails.getYear());
                }
                return carRepository.save(existingCar);
            } else {
                throw new CustomExceptions.CarNotFoundException("Car not found");
            }
        } catch (Exception e) {
            throw new CustomExceptions.CarNotFoundException("Car not found");
        }
    }

    public void deleteCar(UUID carId) throws CustomExceptions.CarNotFoundException{
        Optional<CarModel> existingCar = carRepository.findById(carId);
        if(existingCar.isPresent()){
            carRepository.delete(existingCar.get());
        }
        else{
            throw new CustomExceptions.CarNotFoundException("Car not found");
        }
    }
}
