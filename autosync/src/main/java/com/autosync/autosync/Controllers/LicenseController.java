package com.autosync.autosync.Controllers;

import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Models.LicenseModel;
import com.autosync.autosync.Services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    @Autowired
    LicenseService licenseService;

    @PostMapping("/create")
    public ResponseEntity<LicenseModel> createLicense(@RequestBody LicenseModel license) {
        LicenseModel savedLicense = licenseService.createLicense(license);
        return new ResponseEntity<>(savedLicense, HttpStatus.CREATED);
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<LicenseModel> getLicenseById(@PathVariable UUID licenseId) {
        LicenseModel license = licenseService.getLicenseById(licenseId);
        return new ResponseEntity<>(license, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LicenseModel>> getAllLicenses() {
        List<LicenseModel> licenses = licenseService.getAllLicenses();
        return new ResponseEntity<>(licenses, HttpStatus.OK);
    }

    @PutMapping("/{licenseId}")
    public ResponseEntity<LicenseModel> updateLicense(@PathVariable UUID licenseId, @RequestBody LicenseModel licenseDetails) {
        LicenseModel updatedLicense = licenseService.updateLicense(licenseId, licenseDetails);
        return new ResponseEntity<>(updatedLicense, HttpStatus.OK);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<Map<String, String>> deleteLicense(@PathVariable UUID licenseId) {
        licenseService.deleteLicense(licenseId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "License Deleted");

        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

}
