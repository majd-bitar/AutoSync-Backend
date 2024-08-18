package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {
}
