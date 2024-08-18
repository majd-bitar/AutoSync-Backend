package com.autosync.autosync.repositories;

import com.autosync.autosync.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
}
