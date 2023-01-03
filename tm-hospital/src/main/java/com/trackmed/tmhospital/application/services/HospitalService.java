package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.entities.Hospital;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository repository;

    /** TODO: Método para testes na fase de desenvolvimento, remover */
    public List<Hospital> findAllHospital() {
        return repository.findAll();
    }

    public Hospital findHospitalById(UUID id) {
        Optional<Hospital> hospital = repository.findById(id);
        if(hospital.isEmpty()) {
            throw new HospitalException("Não existe hospital com o código " + id + " cadastrado!");
        }
        if(!hospital.get().isOperationalLicense()) {
            throw new HospitalException("O hospital  " + hospital.get().getName() + " está com sua licença inativa!");
        }
        return hospital.get();
    }

    public boolean existsHospital(UUID id) {
        return repository.findById(id).isPresent();
    }

    public Hospital saveHospital(Hospital hospital) {
        validateOnSaveHospital(hospital);
        return repository.save(hospital);
    }

    private void validateOnSaveHospital(Hospital hospital) {
        if(hospital == null) {
            throw new HospitalException("Não existe hospital informado!");
        }
        if(hospital.getId() != null && existsHospital(hospital.getId())) {
            throw new HospitalException("Jã existe um hospital com o código " + hospital.getId() + " cadastrado!");
        }
    }

    private void validateOnUpdateHospital(Hospital hospital) {
        if(hospital == null) {
            throw new HospitalException("Não existe hospital informado!");
        }
        if(!existsHospital(hospital.getId())) {
            throw new HospitalException("Não existe hospital cadastrado!");
        }
    }

    public Hospital updateHospital(Hospital hospital) {
        validateOnUpdateHospital(hospital);
        return repository.save(hospital);
    }

    public void deleteHospital(UUID id) {
        Optional<Hospital> hospital = repository.findById(id);
        if(hospital.isEmpty()) {
            throw new HospitalException("Não existe hospital com o código " + id + "!");
        }
        hospital.get().setOperationalLicense(false);
        repository.save(hospital.get());
    }
}
