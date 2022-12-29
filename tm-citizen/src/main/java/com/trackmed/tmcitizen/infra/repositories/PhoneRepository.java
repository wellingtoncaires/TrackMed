package com.trackmed.tmcitizen.infra.repositories;

import com.trackmed.tmcitizen.domains.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
