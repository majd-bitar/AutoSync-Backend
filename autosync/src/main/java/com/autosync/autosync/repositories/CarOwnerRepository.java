package com.autosync.autosync.repositories;

import com.autosync.autosync.models.CarOwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarOwnerRepository extends JpaRepository<CarOwnerModel, UUID> {
}
