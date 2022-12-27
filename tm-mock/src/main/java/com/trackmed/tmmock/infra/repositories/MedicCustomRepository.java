package com.trackmed.tmmock.infra.repositories;

import com.trackmed.tmmock.domains.entities.Medic;
import com.trackmed.tmmock.domains.enums.Speciality;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MedicCustomRepository {

    private final EntityManager entityManager;

    public List<Medic> findBySpeciality(Speciality speciality) {
        String query = "select m from Medic as m " +
                "where m.listRegulatoryMedicBody.speciality = :speciality";

        var param = entityManager.createQuery(query, Medic.class);
        param.setParameter("speciality", speciality);

        return param.getResultList();
    }
}
