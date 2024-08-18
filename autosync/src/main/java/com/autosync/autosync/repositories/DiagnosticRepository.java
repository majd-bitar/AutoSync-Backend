package com.autosync.autosync.repositories;

import com.autosync.autosync.models.DiagnosticModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiagnosticRepository extends JpaRepository<DiagnosticModel, UUID> {
}
