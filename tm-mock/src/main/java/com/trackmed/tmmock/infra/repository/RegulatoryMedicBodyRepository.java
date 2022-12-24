package com.trackmed.tmmock.infra.repository;

import com.trackmed.tmmock.domains.RegulatoryMedicBody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegulatoryMedicBodyRepository extends JpaRepository<RegulatoryMedicBody, UUID> {
}
