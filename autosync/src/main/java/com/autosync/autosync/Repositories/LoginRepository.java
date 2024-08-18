package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
}
