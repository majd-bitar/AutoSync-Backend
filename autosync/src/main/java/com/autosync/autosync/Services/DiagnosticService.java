package com.autosync.autosync.Services;


import com.autosync.autosync.ExceptionHandling.CustomExceptions;
import com.autosync.autosync.Models.DiagnosticModel;
import com.autosync.autosync.Repositories.DiagnosticRepository;
import jdk.jshell.Diag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiagnosticService {

    @Autowired
    DiagnosticRepository diagnosticRepository;

    public DiagnosticModel createDiagnostic(DiagnosticModel diagnostic) {
        return diagnosticRepository.save(diagnostic);
    }

    public DiagnosticModel getDiagnosticById(UUID diagnosticId) {
        Optional<DiagnosticModel> diagnosticOptional = diagnosticRepository.findById(diagnosticId);
        if (diagnosticOptional.isPresent()) {
            return diagnosticOptional.get();
        } else {
            throw new CustomExceptions.DiagnosticNotFoundException("Diagnostic not found");
        }
    }

    public DiagnosticModel getDiagnosticsByCarId(UUID carId) {
        try {
            Optional<DiagnosticModel> optionalDiagnosticModel= diagnosticRepository.findLatestDiagnosticByCarId(carId);
            if(optionalDiagnosticModel.isPresent()){
                return optionalDiagnosticModel.get();
            }
            else{
                throw new CustomExceptions.DiagnosticNotFoundException("This car doesn't have diagnostics yet");
            }
        } catch (Exception e) {
            throw new CustomExceptions.DiagnosticNotFoundException("Error retrieving diagnostics for car");
        }
    }


    public void deleteDiagnostic(UUID diagnosticId) {
        Optional<DiagnosticModel> existingDiagnostic = diagnosticRepository.findById(diagnosticId);
        if (existingDiagnostic.isPresent()) {
            diagnosticRepository.delete(existingDiagnostic.get());
        } else {
            throw new CustomExceptions.DiagnosticNotFoundException("Diagnostic not found");
        }
    }

}