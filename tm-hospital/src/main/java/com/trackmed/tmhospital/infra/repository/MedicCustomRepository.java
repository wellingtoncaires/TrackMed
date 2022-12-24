package com.trackmed.tmhospital.infra.repository;

import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.enums.Speciality;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MedicCustomRepository {

    private final EntityManager entityManager;

    public List<Medic> findBySpeciality(Speciality speciality) {
        String query = "select M from Medic as M " +
                "where M.specialities in ( :speciality)";

        var param = entityManager.createQuery(query, Medic.class);
        param.setParameter("speciality", speciality);

        return param.getResultList();
    }

//    public List<Medic> findByRegulatoryMedicBody(RegulatoryMedicBody regulatoryMedicBody) {
//        String query = "select M from Medic as M " +
//                "where M.listRegulatoryMedicBody in ( :regulatoryMedicBody)";
//
//        var param = entityManager.createQuery(query, Medic.class);
//        param.setParameter("regulatoryMedicBody", regulatoryMedicBody);
//
//        return param.getResultList();
//    }
}
