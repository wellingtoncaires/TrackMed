package com.trackmed.tmhospital.infra.repositories;


import com.trackmed.tmhospital.domains.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {;
}
