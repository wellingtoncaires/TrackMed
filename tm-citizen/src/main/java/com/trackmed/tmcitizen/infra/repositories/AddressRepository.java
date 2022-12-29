package com.trackmed.tmcitizen.infra.repositories;

import com.trackmed.tmcitizen.domains.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Optional<Address> findByCep(String cep);
}
