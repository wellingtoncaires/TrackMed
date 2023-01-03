package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.entities.Hospital;
import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.entities.RegulatoryMedicBody;
import com.trackmed.tmhospital.domains.enums.Speciality;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.repositories.MedicCustomRepository;
import com.trackmed.tmhospital.infra.repositories.MedicRepository;
import com.trackmed.tmhospital.infra.repositories.RegulatoryMedicBodyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicService {

    private final MedicRepository medicRepository;
    private final MedicCustomRepository medicCustomRepository;
    private final HospitalService hospitalService;
    private final RegulatoryMedicBodyRepository regulatoryMedicBodyRepository;

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
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        return medicRepository.findByHospital(hospital);
    }


//    public List<Medic> findMedicByRegulatoryMedicBody(RegulatoryMedicBody regulatoryMedicBody) {
//        return medicCustomRepository.findByRegulatoryMedicBody(regulatoryMedicBody);
//    }

    public List<Speciality> getAllSpecialities() {
        return new ArrayList<>(EnumSet.allOf(Speciality.class));
    }

//    public List<Medic> findMedicsBySpeciality(String specialityString) {
//        List<Speciality> specialities = getAllSpecialities();
//        specialities
//                .stream()
//                .filter(splt -> splt.getDescription().equals(specialityString))
//                .findFirst()
//                .orElseThrow((() -> new HospitalException("Especialidade não encontra!")));
//        Speciality speciality = Speciality.valueOf(specialityString);
//        return medicCustomRepository.findBySpeciality(speciality);
//    }

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

    private void validateOnUpdateMedic(Medic medic) {
        if(medic == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
        if(!existsMedic(medic.getId())) {
            throw new HospitalException("Não existe médico cadastrado!");
        }
        if(medic.getHospital() == null) {
            throw new HospitalException("Médico precisa ter um hospital cadastrado!");
        }
    }

    public Medic updateMedic(Medic medic) {
        medic.setPassword(new BCryptPasswordEncoder().encode(medic.getPassword()));
        validateOnUpdateMedic(medic);
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
        }
        if(medicId == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
        Hospital hospital = hospitalService.findHospitalById(hospitalId);
        Medic medic = findMedicById(medicId);
        medic.setHospital(hospital);
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

    public void validateRegisterUpdateToMedic(Medic medic, RegulatoryMedicBody regulatoryMedicBody) {
        if(regulatoryMedicBody == null) {
            throw new HospitalException("Nenhum registro informado!");
        }if(medic == null) {
            throw new HospitalException("Nenhum médico informado!");
        }
        if(regulatoryMedicBody.getSpeciality() == null) {
            throw new HospitalException("Nenhuma especialidade informada!");
        }
        if(!existsMedic(medic.getId())) {
            throw new HospitalException("Não existe médico com o código " + medic.getId() + " cadsatrado!");
        }
        List<Speciality> specialities = new ArrayList<>(EnumSet.allOf(Speciality.class));
        if(!specialities.contains(regulatoryMedicBody.getSpeciality())) {
            throw new HospitalException("Especialidade inválida.<br>Verifique!");
        }
    }

    @Transactional
    public void addRegisterToMedic(UUID idMedic, RegulatoryMedicBody regulatoryMedicBody) {
        Medic medic = findMedicById(idMedic);
        validateRegisterUpdateToMedic(medic, regulatoryMedicBody);
        regulatoryMedicBody.setRegistrationDate(new Date());
        regulatoryMedicBody.setMedicName(medic.getName());
        regulatoryMedicBody.setMedicLastName(medic.getLastName());
        regulatoryMedicBody.setEnabled(true);
        regulatoryMedicBody = regulatoryMedicBodyRepository.save(regulatoryMedicBody);
        medic.getListRegulatoryMedicBody().add(regulatoryMedicBody);
        medicRepository.save(medic);
    }

    @Transactional
    public void removeRegisterToMedic(UUID idMedic, UUID idRegulatory) {
        Medic medic = findMedicById(idMedic);
        RegulatoryMedicBody regulatoryMedicBody = findRegulatoryMedicBodyById(idRegulatory);
        validateRegisterUpdateToMedic(medic, regulatoryMedicBody);
        List<RegulatoryMedicBody> registers = medic.getListRegulatoryMedicBody();
        if(!registers.contains(regulatoryMedicBody)) {
            throw new HospitalException("Registro não localizado para o médico " + medic.getName());
        }
        registers.remove(regulatoryMedicBody);
        medic.setListRegulatoryMedicBody(registers);
        medicRepository.save(medic);
    }

    public RegulatoryMedicBody findRegulatoryMedicBodyById(UUID idRegulatory) {
        Optional<RegulatoryMedicBody> register = regulatoryMedicBodyRepository.findById(idRegulatory);
        if(register.isEmpty()) {
            throw new HospitalException("Registro não loacalizado!");
        }
        return register.get();
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
}
