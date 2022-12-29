package com.trackmed.tmcitizen.infra.repositories;

import com.trackmed.tmcitizen.domains.entities.AuthorizedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorizedPersonRepository extends JpaRepository<AuthorizedPerson, UUID> {
}
