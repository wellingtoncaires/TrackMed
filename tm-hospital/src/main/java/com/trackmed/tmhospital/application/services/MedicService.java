package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.model.Hospital;
import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.enums.Speciality;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.clients.MockResourceClient;
import com.trackmed.tmhospital.infra.repositories.MedicCustomRepository;
import com.trackmed.tmhospital.infra.repositories.MedicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicService {

    private final MedicRepository medicRepository;
    private final MedicCustomRepository medicCustomRepository;
    private final MockResourceClient hospitalService;

    /** start class Medic */
    /** TODO: Método para testes na fase de desenvolvimento, remover */
    public List<Medic> findAllMedics() {
        return medicRepository.findAll();
    }

    public Medic findMedicById(UUID id) {
        Optional<Medic> medic = medicRepository.findById(id);
        if(medic.isEmpty()) {
            throw new HospitalException("Não existe médico com o código " + id + " cadastrado!");
        }
        return medic.get();
    }

    public Medic findMedicByCpf(String cpf) {
        Optional<Medic> medic = medicRepository.findByCpf(cpf);
        if(medic.isEmpty()) {
            throw new HospitalException("Não existe médico com o CPF " + cpf + " cadastrado!");
        }
        return medic.get();
    }

    public Medic findMedicByUsername(String username) {
        Optional<Medic> medic = medicRepository.findByUsername(username);
        if(medic.isEmpty()) {
            throw new HospitalException("Não existe médico com o usrname " + username + " cadastrado!");
        }
        return medic.get();
    }

    public Medic findMedicByEmail(String email) {
        Optional<Medic> medic = medicRepository.findByEmail(email);
        if(medic.isEmpty()) {
            throw new HospitalException("Não existe médico com o e-mail " + email + " cadastrado!");
        }
        return medic.get();
    }

    public List<Medic> findMedicByHospitalId(UUID hospitalId) {
        Hospital hospital = findHospitalById(hospitalId);
        return medicRepository.findByHospital(hospital);
    }

    public Hospital findHospitalById(UUID id) {
        return  hospitalService.findHospital(id).getBody();
    }

//    public List<Medic> findMedicByRegulatoryMedicBody(RegulatoryMedicBody regulatoryMedicBody) {
//        return medicCustomRepository.findByRegulatoryMedicBody(regulatoryMedicBody);
//    }

    public List<Speciality> getAllSpecialities() {
        return new ArrayList<>(EnumSet.allOf(Speciality.class));
    }

    public List<Medic> findMedicsBySpeciality(String specialityString) {
        List<Speciality> specialities = getAllSpecialities();
        specialities
                .stream()
                .filter(splt -> splt.getDescription().equals(specialityString))
                .findFirst()
                .orElseThrow((() -> new HospitalException("Especialidade não encontra!")));
        Speciality speciality = Speciality.valueOf(specialityString);
        return medicCustomRepository.findBySpeciality(speciality);
    }

    public boolean existsMedic(UUID id) {
        return medicRepository.findById(id).isPresent();
    }

    public Medic saveMedic(Medic medic) {
        validateOnSaveMedic(medic);
        medic.setPassword(new BCryptPasswordEncoder().encode(medic.getPassword()));
        return medicRepository.save(medic);
    }

    private void validateOnSaveMedic(Medic medic) {
        if(medic == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
//        if(medic.getId() != null && hospitalService.existsHospital(medic.getId())) {
//            throw new HospitalException("Já existe um médico com o código " + medic.getId() + " cadastrado!");
//        }
    }

    private void validateOnUpdateMedic(Medic medic, UUID id) {
        if(medic == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
        if(!existsMedic(id)) {
            throw new HospitalException("Não existe médico cadastrado!");
        }
        if(!medic.getId().equals(id)) {
            throw new HospitalException("Código do médico diferente do código passado!");
        }
    }

    public Medic updateMedic(Medic medic, UUID id) {
        medic.setPassword(new BCryptPasswordEncoder().encode(medic.getPassword()));
        validateOnUpdateMedic(medic, id);
        return medicRepository.save(medic);
    }

    public void deleteMedic(UUID id) {
        Optional<Medic> medic = medicRepository.findById(id);
        if(medic.isEmpty()) {
            throw new HospitalException("Não existe médico com o código " + id + "!");
        }
        medicRepository.delete(medic.get());
    }

    public void addHospitalToMedic(UUID medicId, UUID hospitalId) {
        if(hospitalId == null) {
            throw new HospitalException("Nenhum hospital informado!");
        }if(medicId == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
        Hospital hospital = findHospitalById(hospitalId);
        Medic medic = findMedicById(medicId);
        medic.setHospital(hospital.getId());
        medicRepository.save(medic);
    }

    public void validateSpecialityUpdateToMedic(Medic medic, Speciality speciality) {
        if(speciality == null) {
            throw new HospitalException("Nenhuma especialidade informada!");
        }if(medic == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
        if(!existsMedic(medic.getId())) {
            throw new HospitalException("Não existe médico com o código " + medic.getId() + " cadsatrado!");
        }
    }

//    public void addSpecialityToMedic(Medic medic, Speciality speciality) {
//        validateSpecialityUpdateToMedic(medic, speciality);
//        medic.getSpecialities().add(speciality);
//        medicRepository.save(medic);
//    }
//
//    public void removeSpecialityToMedic(Medic medic, Speciality speciality) {
//        validateSpecialityUpdateToMedic(medic, speciality);
//        List<Speciality> specialities = medic.getSpecialities();
//        if(specialities.contains(speciality)) {
//            throw new HospitalException("O Médico " + medic.getName() + " não é " + speciality.getDescription());
//        }
//        specialities.remove(speciality);
//        medic.setSpecialities(specialities);
//        medicRepository.save(medic);
//    }
}
