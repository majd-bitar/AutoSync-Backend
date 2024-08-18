package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<ProfileModel, UUID> {
}
