package com.trackmed.tmmock.application.services;

import com.trackmed.tmmock.domains.Medic;
import com.trackmed.tmmock.domains.RegulatoryMedicBody;
import com.trackmed.tmmock.domains.enums.Speciality;
import com.trackmed.tmmock.exceptions.MockException;
import com.trackmed.tmmock.infra.repository.MedicCustomRepository;
import com.trackmed.tmmock.infra.repository.MedicRepository;
import com.trackmed.tmmock.infra.repository.RegulatoryMedicBodyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MedicService {

    private final MedicRepository medicRepository;
    private final MedicCustomRepository medicCustomRepository;
    private final RegulatoryMedicBodyRepository regulatoryMedicBodyRepository;

    public List<Medic> findAllMedics() {
        return medicRepository.findAll();
    }

    public Medic findMedicById(UUID id) {
        Optional<Medic> medic = medicRepository.findById(id);
        if(medic.isEmpty()) {
            throw new MockException("Não existe médico com o código " + id + " cadastrado!");
        }
        return medic.get();
    }

    public Medic findMedicByCpf(String cpf) {
        Optional<Medic> medic = medicRepository.findByCpf(cpf);
        if(medic.isEmpty()) {
            throw new MockException("Não existe médico com o CPF " + cpf + " cadastrado!");
        }
        return medic.get();
    }

    public List<Speciality> getAllSpecialities() {
        return new ArrayList<>(EnumSet.allOf(Speciality.class));
    }

    public boolean existsMedic(UUID id) {
        return medicRepository.findById(id).isPresent();
    }

    public boolean existsMedic(String cpf) {
        return medicRepository.findByCpf(cpf).isPresent();
    }

    public Medic saveMedic(Medic medic) {
        validateOnSaveMedic(medic);
        return medicRepository.save(medic);
    }

    private void validateOnSaveMedic(Medic medic) {
        if(medic == null) {
            throw new MockException("Nenhum médico informado!");
        }
        if(medic.getCpf() == null || medic.getCpf().trim().equals("")) {
            /** TODO: Criar regra de validação de CPF quando for migrar para Banco de dados */
            throw new MockException("Cpf inválido!");
        }
        if(existsMedic(medic.getCpf())) {
            throw new MockException("Médico já cadastrado!");
        }
    }

    private void validateOnUpdateMedic(Medic medic) {
        if(medic == null) {
            throw new MockException("Nenhum médico informado!");
        }
        if(!existsMedic(medic.getId())) {
            throw new MockException("Não existe médico cadastrado!");
        }
    }

    public Medic updateMedic(Medic medic) {
        validateOnUpdateMedic(medic);
        return medicRepository.save(medic);
    }

    public void validateRegisterUpdateToMedic(Medic medic, RegulatoryMedicBody regulatoryMedicBody) {
        if(regulatoryMedicBody == null) {
            throw new MockException("Nenhum registro informado!");
        }if(medic == null) {
            throw new MockException("Nenhum médico informado!");
        }
        if(regulatoryMedicBody.getSpeciality() == null) {
            throw new MockException("Nenhuma especialidade informada!");
        }
        if(!existsMedic(medic.getId())) {
            throw new MockException("Não existe médico com o código " + medic.getId() + " cadsatrado!");
        }
        List<Speciality> specialities = new ArrayList<>(EnumSet.allOf(Speciality.class));
        if(!specialities.contains(regulatoryMedicBody.getSpeciality())) {
            throw new MockException("Especialidade inválida.<br>Verifique!");
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
            throw new MockException("Registro não localizado para o médico " + medic.getName());
        }
        registers.remove(regulatoryMedicBody);
        medic.setListRegulatoryMedicBody(registers);
        medicRepository.save(medic);
    }

    public RegulatoryMedicBody findRegulatoryMedicBodyById(UUID idRegulatory) {
        Optional<RegulatoryMedicBody> register = regulatoryMedicBodyRepository.findById(idRegulatory);
        if(register.isEmpty()) {
            throw new MockException("Registro não loacalizado!");
        }
        return register.get();
    }

    public List<Medic> findMedicsBySpeciality(String specialityString) {
        List<Speciality> specialities = getAllSpecialities();
        specialities
                .stream()
                .filter(splt -> splt.getDescription().equals(specialityString))
                .findFirst()
                .orElseThrow((() -> new MockException("Especialidade não encontra!")));
        log.info("findMedicsBySpeciality");
        Speciality speciality = Speciality.valueOf(specialityString);
        return medicCustomRepository.findBySpeciality(speciality);
    }
}
