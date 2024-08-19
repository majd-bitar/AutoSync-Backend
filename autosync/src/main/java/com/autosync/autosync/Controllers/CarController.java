package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.CarModel;
import com.autosync.autosync.Models.ProfileModel;
import com.autosync.autosync.Repositories.CarRepository;
import com.autosync.autosync.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
