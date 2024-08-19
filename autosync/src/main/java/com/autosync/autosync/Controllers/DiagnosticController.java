package com.autosync.autosync.Controllers;

import com.autosync.autosync.Models.DiagnosticModel;
import com.autosync.autosync.Services.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticController {

    @Autowired
    DiagnosticService diagnosticService;

    @PostMapping("/create")
    public ResponseEntity<DiagnosticModel> createDiagnostic(@RequestBody DiagnosticModel diagnostic) {
        DiagnosticModel savedDiagnostic = diagnosticService.createDiagnostic(diagnostic);
        return new ResponseEntity<>(savedDiagnostic, HttpStatus.CREATED);
    }

    @GetMapping("/{diagnosticId}")
    public ResponseEntity<DiagnosticModel> getDiagnosticById(@PathVariable UUID diagnosticId) {
        DiagnosticModel diagnostic = diagnosticService.getDiagnosticById(diagnosticId);
        return new ResponseEntity<>(diagnostic, HttpStatus.OK);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<DiagnosticModel> getDiagnosticsByCarId(@PathVariable UUID carId) {
        DiagnosticModel diagnostics = diagnosticService.getDiagnosticsByCarId(carId);
        return new ResponseEntity<>(diagnostics, HttpStatus.OK);
    }

    @DeleteMapping("/{diagnosticId}")
    public ResponseEntity<Map<String, String>> deleteDiagnostic(@PathVariable UUID diagnosticId) {
        diagnosticService.deleteDiagnostic(diagnosticId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Diagnostic Deleted");

        return new ResponseEntity<>(response, HttpStatus.GONE);
    }
}

