package com.trackmed.tmhospital.infra.repositories;

import com.trackmed.tmhospital.domains.entities.Hospital;
import com.trackmed.tmhospital.domains.entities.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicRepository extends JpaRepository<Medic, UUID> {

    Optional<Medic> findByUsername(String username);
    Optional<Medic> findByEmail(String email);
    Optional<Medic> findByCpf(String cpf);
    List<Medic> findByHospital(Hospital hospital);
}
