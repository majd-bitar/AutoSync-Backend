package com.autosync.autosync.Controllers;

import com.autosync.autosync.Models.CarModel;
import com.autosync.autosync.Models.CarOwnerModel;
import com.autosync.autosync.Services.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/carOwner")
public class CarOwnerController {

    @Autowired
    private CarOwnerService carOwnerService;

    @PostMapping("/create")
    public ResponseEntity<CarOwnerModel> createCarOwner(@RequestBody CarOwnerModel carOwner) {
        CarOwnerModel savedCarOwner = carOwnerService.createCarOwner(carOwner);
        return new ResponseEntity<>(savedCarOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{carOwnerId}")
    public ResponseEntity<CarOwnerModel> getCarOwnerById(@PathVariable UUID carOwnerId) {
        CarOwnerModel carOwner = carOwnerService.getCarOwnerById(carOwnerId);
        return new ResponseEntity<>(carOwner, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarOwnerModel>> getAllCarOwners() {
        List<CarOwnerModel> carOwners = carOwnerService.getAllCarOwners();
        return new ResponseEntity<>(carOwners, HttpStatus.OK);
    }

    @DeleteMapping("/{carOwnerId}")
    public ResponseEntity<Map<String, String>> deleteCarOwner(@PathVariable UUID carOwnerId) {
        carOwnerService.deleteCarOwner(carOwnerId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Car owner deleted");
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @PostMapping("/{carOwnerId}/associateCar")
    public ResponseEntity<CarOwnerModel> associateCarWithOwner(@PathVariable UUID carOwnerId, @RequestBody CarModel car) {
        CarOwnerModel updatedCarOwner = carOwnerService.associateCarWithOwner(carOwnerId, car);
        return new ResponseEntity<>(updatedCarOwner, HttpStatus.OK);
    }
}
