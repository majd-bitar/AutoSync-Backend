package com.autosync.autosync.repositories;

import com.autosync.autosync.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
}
