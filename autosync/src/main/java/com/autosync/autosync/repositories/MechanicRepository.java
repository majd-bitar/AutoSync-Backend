package com.autosync.autosync.repositories;

import com.autosync.autosync.models.MechanicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MechanicRepository extends JpaRepository<MechanicModel, UUID> {
}
