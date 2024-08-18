package com.autosync.autosync.repositories;

import com.autosync.autosync.models.LicenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LicenseRepository extends JpaRepository<LicenseModel, UUID> {
}
