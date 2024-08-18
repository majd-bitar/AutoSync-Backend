package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.MechanicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MechanicRepository extends JpaRepository<MechanicModel, UUID> {
}
