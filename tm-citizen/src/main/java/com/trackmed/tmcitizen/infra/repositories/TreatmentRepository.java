package com.trackmed.tmcitizen.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentRepository, UUID> {
}
