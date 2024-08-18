package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.DiagnosticModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiagnosticRepository extends JpaRepository<DiagnosticModel, UUID> {
}
