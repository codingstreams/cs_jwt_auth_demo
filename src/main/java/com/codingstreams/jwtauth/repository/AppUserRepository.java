package com.codingstreams.jwtauth.repository;

import com.codingstreams.jwtauth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    boolean existsByUsername(String username);

    Optional<AppUser> findByUsername(String username);
}
