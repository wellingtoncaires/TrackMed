package com.trackmed.tmbase.infra.repositories;

import com.trackmed.tmbase.domain.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailRespository extends JpaRepository<Email, UUID> {

}
