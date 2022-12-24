package com.trackmed.tmmock.infra.repository;

import com.trackmed.tmmock.domains.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicRepository extends JpaRepository<Medic, UUID> {

    Optional<Medic> findByCpf(String cpf);
}
