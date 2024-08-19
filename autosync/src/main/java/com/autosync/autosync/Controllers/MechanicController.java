package com.autosync.autosync.Controllers;

import com.autosync.autosync.Models.MechanicModel;
import com.autosync.autosync.Services.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mechanic")
public class MechanicController {

    @Autowired
    MechanicService mechanicService;

    @PostMapping("/create")
    public ResponseEntity<MechanicModel> createMechanic(@RequestBody MechanicModel mechanic) {
        MechanicModel savedMechanic = mechanicService.createMechanic(mechanic);
        return new ResponseEntity<>(savedMechanic, HttpStatus.CREATED);
    }

    @GetMapping("/{mechanicId}")
    public ResponseEntity<MechanicModel> getMechanicById(@PathVariable UUID mechanicId) {
        MechanicModel mechanic = mechanicService.getMechanicById(mechanicId);
        return new ResponseEntity<>(mechanic, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<MechanicModel>> getAllMechanics() {
        List<MechanicModel> mechanics = mechanicService.getAllMechanics();
        return new ResponseEntity<>(mechanics, HttpStatus.OK);
    }

    @DeleteMapping("/{mechanicId}")
    public ResponseEntity<Map<String, String>> deleteMechanic(@PathVariable UUID mechanicId) {
        mechanicService.deleteMechanic(mechanicId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Mechanic Deleted");

        return new ResponseEntity<>(response, HttpStatus.GONE);
    }
}
