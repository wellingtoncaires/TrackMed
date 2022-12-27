package com.trackmed.tmmock.infra.repositories;

import com.trackmed.tmmock.domains.entities.RegulatoryMedicBody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegulatoryMedicBodyRepository extends JpaRepository<RegulatoryMedicBody, UUID> {
}
