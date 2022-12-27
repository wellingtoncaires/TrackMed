package com.trackmed.tmmock.application.services;


import com.trackmed.tmmock.domains.entities.Pharmaceutical;
import com.trackmed.tmmock.domains.entities.RegulatoryPharmaceuticalBody;
import com.trackmed.tmmock.exceptions.MockException;
import com.trackmed.tmmock.infra.repositories.PharmaceuticalRepository;
import com.trackmed.tmmock.infra.repositories.RegulatoryPharmaceuticalBodyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PharmaceuticalService {

    private final PharmaceuticalRepository pharmaceuticalRepository;
    private final RegulatoryPharmaceuticalBodyRepository regulatoryPharmaceuticalBodyRepository;

    public List<Pharmaceutical> findAllPharmaceuticals() {
        return pharmaceuticalRepository.findAll();
    }

    public Pharmaceutical findPharmaceuticalById(UUID id) {
        Optional<Pharmaceutical> pharmaceutical = pharmaceuticalRepository.findById(id);
        if (pharmaceutical.isEmpty()) {
            throw new MockException("Não existe farmacêutico com o código " + id + " cadasrado!");
        }
        return pharmaceutical.get();
    }

    public Pharmaceutical findPharmaceuticalByCpf(String cpf) {
        Optional<Pharmaceutical> pharmaceutical = pharmaceuticalRepository.findByCpf(cpf);
        if (pharmaceutical.isEmpty()) {
            throw new MockException("Não existe farmacêutico com o cpf " + cpf + " cadasrado!");
        }
        return pharmaceutical.get();
    }

    public boolean existsPharmaceutical(UUID id) {
        return pharmaceuticalRepository.findById(id).isPresent();
    }

    public boolean existsPharmaceutical(String cpf) {
        return pharmaceuticalRepository.findByCpf(cpf).isPresent();
    }

    public Pharmaceutical savePharmaceutical(Pharmaceutical pharmaceutical) {
        validateOnSavePharmaceutical(pharmaceutical);
        return pharmaceuticalRepository.save(pharmaceutical);
    }

    public void validateOnSavePharmaceutical(Pharmaceutical pharmaceutical) {
        if (pharmaceutical == null) {
            throw new MockException("Nenhum faracêutico informado!");
        }
        if (pharmaceutical.getCpf() == null || pharmaceutical.getCpf().trim().equals("")) {
            /** TODO: Criar regra de validação de CPF quando for migrar para Banco de dados */
            throw new MockException("Cpf inválido!");
        }
        if (existsPharmaceutical(pharmaceutical.getCpf())) {
            throw new MockException("Faracêutico já cadastrado!");
        }
    }

    public Pharmaceutical updatePharmaceutical(Pharmaceutical pharmaceutical) {
        validateOnUpdatePharmaceutical(pharmaceutical);
        return pharmaceuticalRepository.save(pharmaceutical);
    }

    public void validateOnUpdatePharmaceutical(Pharmaceutical pharmaceutical) {
        if(pharmaceutical == null) {
            throw new MockException("Nenhum médico informado!");
        }
        if(!existsPharmaceutical(pharmaceutical.getId())) {
            throw new MockException("Não existe médico cadastrado!");
        }
    }

    @Transactional
    public void addRegisterToPharmaceutical(UUID idPharmaceutical, RegulatoryPharmaceuticalBody regulatoryPharmaceuticalBody) {
        Pharmaceutical pharmaceutical = findPharmaceuticalById(idPharmaceutical);
        validateRegisterToPharmaceutical(pharmaceutical, regulatoryPharmaceuticalBody);
        regulatoryPharmaceuticalBody.setRegistrationDate(new Date());
        regulatoryPharmaceuticalBody.setPharmaceuticalName(pharmaceutical.getName());
        regulatoryPharmaceuticalBody.setPharmaceuticalLastName(pharmaceutical.getLastName());
        regulatoryPharmaceuticalBody.setEnabled(true);
        regulatoryPharmaceuticalBodyRepository.save(regulatoryPharmaceuticalBody);
        pharmaceutical.setRegulatoryBody(regulatoryPharmaceuticalBody);
        pharmaceuticalRepository.save(pharmaceutical);
    }

    public void validateRegisterToPharmaceutical(Pharmaceutical pharmaceutical, RegulatoryPharmaceuticalBody regulatoryPharmaceuticalBody) {
        if(regulatoryPharmaceuticalBody == null) {
            throw new MockException("Nenhum registro informado!");
        }if(pharmaceutical == null) {
            throw new MockException("Nenhum famacêutico informado!");
        }
        if(!existsPharmaceutical(pharmaceutical.getId())) {
            throw new MockException("Não existe famacêutico com o código " + pharmaceutical.getId() + " cadsatrado!");
        }
    }

    public void removeRegisterToPharmaceutical(UUID idPharmaceutical) {
        Pharmaceutical pharmaceutical = findPharmaceuticalById(idPharmaceutical);
        RegulatoryPharmaceuticalBody register = pharmaceutical.getRegulatoryBody();
        if(register == null) {
            throw new MockException("Farmacêutico não possui nenhum registro");
        }
        register.setEnabled(false);
        regulatoryPharmaceuticalBodyRepository.save(register);
    }
}
