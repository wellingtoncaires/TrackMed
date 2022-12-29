package com.trackmed.tmcitizen.infra.repositories;

import com.trackmed.tmcitizen.domains.entities.TreatmentShopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreatmentShoppingRepository extends JpaRepository<TreatmentShopping, UUID> {
}
