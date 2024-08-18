package com.autosync.autosync.repositories;

import com.autosync.autosync.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {
}
