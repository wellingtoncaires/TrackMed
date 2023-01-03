package com.trackmed.tmhospital.infra.repositories;

import com.trackmed.tmhospital.domains.entities.RegulatoryMedicBody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegulatoryMedicBodyRepository extends JpaRepository<RegulatoryMedicBody, UUID> {
}
