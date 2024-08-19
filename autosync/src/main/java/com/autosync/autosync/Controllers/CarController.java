package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.CarModel;
import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Repositories.CarRepository;
import com.autosync.autosync.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/create")
    public ResponseEntity<CarModel> createCar(@RequestBody CarModel car){
        CarModel savedCar = carService.createCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarModel> getCarById(@PathVariable UUID carId){
        CarModel retrievedCar = carService.getCarById(carId);
        return new ResponseEntity<>(retrievedCar,HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarModel>> getAllCars(){
        List<CarModel> cars = carService.getAllCars();
        return new ResponseEntity<>(cars,HttpStatus.FOUND);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarModel> updateCar(@PathVariable UUID carId,@RequestBody CarModel car){
        CarModel updatedCar = carService.updateCar(carId,car);
        return new ResponseEntity<>(updatedCar,HttpStatus.OK);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Map<String ,String >> deleteCar(@PathVariable UUID carId){
        carService.deleteCar(carId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Car Deleted");
        return new ResponseEntity<>(response,HttpStatus.GONE);
    }
}
