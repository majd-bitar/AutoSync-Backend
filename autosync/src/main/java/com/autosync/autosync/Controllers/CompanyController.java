package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.CompanyModel;
import com.autosync.autosync.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<CompanyModel> createCompany(@RequestBody CompanyModel company) {
        CompanyModel savedCompany = companyService.createCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyModel> getCompanyById(@PathVariable UUID companyId) {
        CompanyModel company = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        List<CompanyModel> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyModel> updateCompany(@PathVariable UUID companyId, @RequestBody CompanyModel companyDetails) {
        CompanyModel updatedCompany = companyService.updateCompany(companyId, companyDetails);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Map<String, String>> deleteCompany(@PathVariable UUID companyId) {
        companyService.deleteCompany(companyId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Company Deleted");

        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @PostMapping("/{companyId}/linkLicense/{licenseId}")
    public ResponseEntity<CompanyModel> linkLicenseToCompany(@PathVariable UUID companyId, @PathVariable UUID licenseId) {
        CompanyModel company = companyService.linkLicenseToCompany(companyId, licenseId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("/{companyId}/assignMechanic/{mechanicId}")
    public ResponseEntity<CompanyModel> assignMechanicToCompany(@PathVariable UUID companyId, @PathVariable UUID mechanicId) {
        CompanyModel company = companyService.assignMechanicToCompany(companyId, mechanicId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("/{companyId}/removeMechanic/{mechanicId}")
    public ResponseEntity<CompanyModel> removeMechanicFromCompany(@PathVariable UUID companyId, @PathVariable UUID mechanicId) {
        CompanyModel company = companyService.removeMechanicFromCompany(companyId, mechanicId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
