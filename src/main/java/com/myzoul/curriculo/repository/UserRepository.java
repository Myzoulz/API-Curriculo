package com.myzoul.curriculo.repository;

import com.myzoul.curriculo.model.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEnt, Long> {
    Optional<UserEnt> findByEmail(String email);
}
