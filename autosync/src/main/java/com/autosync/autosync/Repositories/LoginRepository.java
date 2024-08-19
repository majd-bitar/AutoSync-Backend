package com.autosync.autosync.Repositories;

import com.autosync.autosync.Models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
    //Spring Data JPA automatically interprets this method name and generates the appropriate query based on the entity's username field.
    Optional<LoginModel> findByUsername(String username);
    
    boolean existsByUsername(String username);
}
