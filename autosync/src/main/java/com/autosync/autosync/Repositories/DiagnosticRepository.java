package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.DiagnosticModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface DiagnosticRepository extends JpaRepository<DiagnosticModel, UUID> {
    // Custom query method to find the latest diagnostic by car ID
    @Query("SELECT d FROM DiagnosticModel d WHERE d.car.carId = :carId ORDER BY d.timestamp DESC")
    Optional<DiagnosticModel> findLatestDiagnosticByCarId(@Param("carId") UUID carId);
}
