package com.trackmed.tmpharmacy.infra.repositories;

import com.trackmed.tmpharmacy.domains.entities.RegulatoryPharmaceuticalBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegulatoryPharmaceuticalBodyRepository extends JpaRepository<RegulatoryPharmaceuticalBody, UUID> {

    Optional<RegulatoryPharmaceuticalBody> findByPharmaceuticalCpf(String cpf);
}
