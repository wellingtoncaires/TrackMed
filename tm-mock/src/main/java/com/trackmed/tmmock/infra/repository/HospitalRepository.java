package com.trackmed.tmmock.infra.repository;


import com.trackmed.tmmock.domains.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {;
}
