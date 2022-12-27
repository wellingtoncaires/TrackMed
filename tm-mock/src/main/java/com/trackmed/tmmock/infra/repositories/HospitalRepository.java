package com.trackmed.tmmock.infra.repositories;


import com.trackmed.tmmock.domains.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {;
}
