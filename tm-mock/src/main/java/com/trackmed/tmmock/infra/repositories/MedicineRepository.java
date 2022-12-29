package com.trackmed.tmmock.infra.repositories;

import com.trackmed.tmmock.domains.entities.Medicine;
import com.trackmed.tmmock.domains.enums.MedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
    List<Medicine> findByActivePrinciple(String activePrinciple);
    List<Medicine> findByMedicineType(MedicineType medicineType);
    Optional<Medicine> findByCommercialName(String commercialName);
    List<Medicine> findByCompany(String company);
}
