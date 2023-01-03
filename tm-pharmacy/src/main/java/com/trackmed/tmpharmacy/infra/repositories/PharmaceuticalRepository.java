package com.trackmed.tmpharmacy.infra.repositories;

import com.trackmed.tmpharmacy.domains.entities.Pharmaceutical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PharmaceuticalRepository extends JpaRepository<Pharmaceutical, UUID> {
    Optional<Pharmaceutical> findByCpf(String cpf);
}
