package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.LicenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LicenseRepository extends JpaRepository<LicenseModel, UUID> {
}
