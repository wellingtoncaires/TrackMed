package com.trackmed.tmmock.infra.repositories;

import com.trackmed.tmmock.domains.entities.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicRepository extends JpaRepository<Medic, UUID> {

    Optional<Medic> findByCpf(String cpf);
}
