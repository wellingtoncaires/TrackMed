package com.trackmed.tmmock.application.services;

import com.trackmed.tmmock.domains.entities.Hospital;
import com.trackmed.tmmock.exceptions.MockException;
import com.trackmed.tmmock.infra.repositories.HospitalRepository;
import com.trackmed.tmmock.infra.repositories.MedicCustomRepository;
import com.trackmed.tmmock.infra.repositories.MedicRepository;
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
    private final MedicRepository medicRepository;
    private final MedicCustomRepository medicCustomRepository;

    /** TODO: Método para testes na fase de desenvolvimento, remover */
    public List<Hospital> findAllHospital() {
        return hospitalRepository.findAll();
    }

    /** Start class Medic */
    public Hospital findHospitalById(UUID id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if(hospital.isEmpty()) {
            throw new MockException("Não existe hospital com o código " + id + " cadastrado!");
        }
        if(!hospital.get().isOperationalLicense()) {
            throw new MockException("O hospital  " + hospital.get().getName() + " está com sua licença inativa!");
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
            throw new MockException("Não existe hospital informado!");
        }
        if(hospital.getId() != null && existsHospital(hospital.getId())) {
            throw new MockException("Jã existe um hospital com o código " + hospital.getId() + " cadastrado!");
        }
    }

    private void validateOnUpdateHospital(Hospital hospital) {
        if(hospital == null) {
            throw new MockException("Não existe hospital informado!");
        }
        if(!existsHospital(hospital.getId())) {
            throw new MockException("Não existe hospital cadastrado!");
        }
    }

    public Hospital updateHospital(Hospital hospital) {
        validateOnUpdateHospital(hospital);
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(UUID id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if(hospital.isEmpty()) {
            throw new MockException("Não existe hospital com o código " + id + "!");
        }
        hospital.get().setOperationalLicense(false);
        hospitalRepository.save(hospital.get());
    }
}
