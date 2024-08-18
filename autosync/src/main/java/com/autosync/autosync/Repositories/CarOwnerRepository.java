package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.CarOwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarOwnerRepository extends JpaRepository<CarOwnerModel, UUID> {
}
