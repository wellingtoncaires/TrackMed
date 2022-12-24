package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.entities.Hospital;
import com.trackmed.tmhospital.domains.entities.Receptionist;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    /** TODO: Método para testes na fase de desenvolvimento, remover */
    public List<Hospital> findAllHospital() {
        return hospitalRepository.findAll();
    }

    public Hospital findHospitalById(UUID id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if(hospital.isEmpty()) {
            throw new HospitalException("Não existe hospital com o código " + id + " cadastrado!");
        }
        return hospital.get();
    }

    public boolean existsHospital(UUID id) {
        return hospitalRepository.findById(id).isPresent();
    }

    public Hospital saveHospital(Hospital hospital) {
        validateOnSaveHospital(hospital);
        return hospitalRepository.save(hospital);
    }

    private void validateOnSaveHospital(Hospital hospital) {
        if(hospital == null) {
            throw new HospitalException("Não existe hospital informado!");
        }
        if(hospital.getId() != null && existsHospital(hospital.getId())) {
            throw new HospitalException("Jã existe um hospital com o código " + hospital.getId() + " cadastrado!");
        }
    }

    private void validateOnUpdateHospital(Hospital hospital, UUID id) {
        if(hospital == null) {
            throw new HospitalException("Não existe hospital informado!");
        }
        if(!existsHospital(id)) {
            throw new HospitalException("Não existe hospital cadastrado!");
        }
        if(!hospital.getId().equals(id)) {
            throw new HospitalException("Código do hospital diferente do código passado!");
        }
    }

    public Hospital updateHospital(Hospital hospital, UUID id) {
        validateOnUpdateHospital(hospital, id);
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(UUID id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if(hospital.isEmpty()) {
            throw new HospitalException("Não existe hospital com o código " + id + "!");
        }
        hospitalRepository.delete(hospital.get());
    }
}
