package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.entities.Hospital;
import com.trackmed.tmhospital.domains.entities.Receptionist;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.repository.ReceptionistRepository;
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
    private final HospitalService hospitalService;
    private final MedicService medicService;

    /** Start class Receptionist */
    public Optional<Receptionist> findReceptionistById(UUID id) {
        Optional<Receptionist> receptionist = receptionistRepository.findById(id);
        if(receptionist.isEmpty()) {
            throw new HospitalException("Não existe recepcionista com o código " + id + " cadastrado!");
        }
        return receptionist;
    }

    public Optional<Receptionist> findReceptionistByUsername(String username) {
        Optional<Receptionist> receptionist = receptionistRepository.findByUsername(username);
        if(receptionist.isEmpty()) {
            throw new HospitalException("Username não localizado!");
        }
        return receptionist;
    }

    public Optional<Receptionist> findReceptionistByEmail(String email) {
        Optional<Receptionist> receptionist = receptionistRepository.findByEmail(email);
        if(receptionist.isEmpty()) {
            throw new HospitalException("Email não localizado!");
        }
        return receptionist;
    }

    public Optional<Receptionist> findReceptionistByCpf(String cpf) {
        Optional<Receptionist> receptionist = receptionistRepository.findByCpf(cpf);
        if(receptionist.isEmpty()) {
            throw new HospitalException("CPF não localizado!");
        }
        return receptionist;
    }

    public List<Receptionist> findReceptionistByHospital(Hospital hospital) {
        List<Receptionist> receptionist = receptionistRepository.findByHospital(hospital);
        return receptionist;
    }

    public boolean existsReceptionist(UUID id) {
        return receptionistRepository.findById(id).isPresent();
    }

    public Receptionist saveReceptionist(Receptionist receptionist) {
        if(existsReceptionist(receptionist.getId())) {
            throw new HospitalException("Receptionist já cadastrada!");
        }
        return receptionistRepository.save(receptionist);
    }

    public Receptionist updateReceptionist(Receptionist receptionist) {
        if(!existsReceptionist(receptionist.getId())) {
            throw new HospitalException("Receptionist não cadastrada cadastrada!");
        }
        return receptionistRepository.save(receptionist);
    }

    public void addHospitalToReceptionist(Receptionist receptionist, Hospital hospital) {
        if(hospital == null) {
            throw new HospitalException("Nenhum hospital informado!");
        }
        if(!hospitalService.existsHospital(hospital.getId())) {
            throw new HospitalException("Não existe hospital com o código " + hospital.getId() + " cadsatrado!");
        }if(receptionist == null) {
            throw new HospitalException("Nenhuma receptionista informada!");
        }
        if(!medicService.existsMedic(receptionist.getId())) {
            throw new HospitalException("Não existe receptionista com o código " + receptionist.getId() + " cadsatrada!");
        }
        receptionist.setHospital(hospital);
        receptionistRepository.save(receptionist);
    }
}
