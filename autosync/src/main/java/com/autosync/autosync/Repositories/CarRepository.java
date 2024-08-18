package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
}
