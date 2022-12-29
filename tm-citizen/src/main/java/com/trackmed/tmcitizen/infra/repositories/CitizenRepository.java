package com.trackmed.tmcitizen.infra.repositories;

import com.trackmed.tmcitizen.domains.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, UUID> {
    Optional<Citizen> findByUsername(String username);
}
