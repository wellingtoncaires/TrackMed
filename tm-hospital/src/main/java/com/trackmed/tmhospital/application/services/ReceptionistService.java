package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.model.Hospital;
import com.trackmed.tmhospital.domains.entities.Receptionist;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.clients.MockResourceClient;
import com.trackmed.tmhospital.infra.repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final MockResourceClient hospitalService;

    public List<Receptionist> findAll() {
        return receptionistRepository.findAll();
    }

    public Receptionist findReceptionistById(UUID id) {
        Optional<Receptionist> receptionist = receptionistRepository.findById(id);
        if(receptionist.isEmpty()) {
            throw new HospitalException("Não existe recepcionista com o código " + id + " cadastrado!");
        }
        return receptionist.get();
    }

    public Receptionist findReceptionistByUsername(String username) {
        Optional<Receptionist> receptionist = receptionistRepository.findByUsername(username);
        if(receptionist.isEmpty()) {
            throw new HospitalException("Username não localizado!");
        }
        return receptionist.get();
    }

    public Receptionist findReceptionistByEmail(String email) {
        Optional<Receptionist> receptionist = receptionistRepository.findByEmail(email);
        if(receptionist.isEmpty()) {
            throw new HospitalException("Email não localizado!");
        }
        return receptionist.get();
    }

    public Receptionist findReceptionistByCpf(String cpf) {
        Optional<Receptionist> receptionist = receptionistRepository.findByCpf(cpf);
        if(receptionist.isEmpty()) {
            throw new HospitalException("CPF não localizado!");
        }
        return receptionist.get();
    }

    public Hospital findHospitalById(UUID id) {
        return hospitalService.findHospital(id).getBody();
    }

    public List<Receptionist> findReceptionistByHospital(UUID idHospital) {
        Hospital hospital = findHospitalById(idHospital);
        List<Receptionist> receptionist = receptionistRepository.findByHospital(hospital);
        return receptionist;
    }

    public boolean existsReceptionist(UUID id) {
        return receptionistRepository.findById(id).isPresent();
    }

    public Receptionist saveReceptionist(Receptionist receptionist, UUID idHospital) {
        Hospital hospital = findHospitalById(idHospital);
        validateOnSaveReceptionist(receptionist, hospital);
        receptionist.setHospital(hospital.getId());
        return receptionistRepository.save(receptionist);
    }

    public void validateOnSaveReceptionist(Receptionist receptionist, Hospital hospital) {
        if(hospital == null) {
            throw new HospitalException("Hospital inválido!");
        }
        if(existsReceptionistByCpf(receptionist.getCpf())) {
            throw new HospitalException("Recepicionista já cadastrada!");
        }
    }

    private boolean existsReceptionistByCpf(String cpf) {
        return receptionistRepository.findByCpf(cpf).isPresent();
    }

    public Receptionist updateReceptionist(Receptionist receptionist) {
        if(!existsReceptionist(receptionist.getId())) {
            throw new HospitalException("Receptionist não cadastrada!");
        }
        return receptionistRepository.save(receptionist);
    }
}
