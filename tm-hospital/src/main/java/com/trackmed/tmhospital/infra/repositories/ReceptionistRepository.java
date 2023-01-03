package com.trackmed.tmhospital.infra.repositories;

import com.trackmed.tmhospital.domains.entities.Hospital;
import com.trackmed.tmhospital.domains.entities.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, UUID> {

    Optional<Receptionist> findById(UUID id);
    Optional<Receptionist> findByUsername(String username);
    Optional<Receptionist> findByEmail(String email);
    Optional<Receptionist> findByCpf(String cpf);
    List<Receptionist> findByHospital(Hospital hospital);
}
