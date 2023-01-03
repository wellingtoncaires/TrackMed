package com.trackmed.tmpharmacy.application.services;


import com.trackmed.tmpharmacy.domains.entities.Pharmaceutical;
import com.trackmed.tmpharmacy.domains.entities.RegulatoryPharmaceuticalBody;
import com.trackmed.tmpharmacy.exceptions.PharmacyException;
import com.trackmed.tmpharmacy.infra.repositories.PharmaceuticalRepository;
import com.trackmed.tmpharmacy.infra.repositories.RegulatoryPharmaceuticalBodyRepository;
import com.trackmed.tmpharmacy.security.SecurityBeans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PharmaceuticalService {

    private final PharmaceuticalRepository repository;
    private final RegulatoryPharmaceuticalBodyRepository regulatoryPharmaceuticalBodyRepository;

    public List<Pharmaceutical> findAllPharmaceuticals() {
        return repository.findAll();
    }

    public Pharmaceutical findPharmaceuticalById(UUID id) {
        Optional<Pharmaceutical> pharmaceutical = repository.findById(id);
        if (pharmaceutical.isEmpty()) {
            throw new PharmacyException("Não existe farmacêutico com o código " + id + " cadasrado!");
        }
        return pharmaceutical.get();
    }

    public Pharmaceutical findPharmaceuticalByCpf(String cpf) {
        Optional<Pharmaceutical> pharmaceutical = repository.findByCpf(cpf);
        if (pharmaceutical.isEmpty()) {
            throw new PharmacyException("Não existe farmacêutico com o cpf " + cpf + " cadasrado!");
        }
        return pharmaceutical.get();
    }

    public boolean existsPharmaceutical(UUID id) {
        return repository.findById(id).isPresent();
    }

    public boolean existsPharmaceutical(String cpf) {
        return repository.findByCpf(cpf).isPresent();
    }

    public Pharmaceutical savePharmaceutical(Pharmaceutical pharmaceutical) {
        validateOnSavePharmaceutical(pharmaceutical);
        pharmaceutical.setPassword(new SecurityBeans().passwordEncoder().encode(pharmaceutical.getPassword()));
        return repository.save(pharmaceutical);
    }

    public void validateOnSavePharmaceutical(Pharmaceutical pharmaceutical) {
        if (pharmaceutical == null) {
            throw new PharmacyException("Nenhum faracêutico informado!");
        }
        if (pharmaceutical.getCpf() == null || pharmaceutical.getCpf().trim().equals("")) {
            /** TODO: Criar regra de validação de CPF quando for migrar para Banco de dados */
            throw new PharmacyException("Cpf inválido!");
        }
        if (existsPharmaceutical(pharmaceutical.getCpf())) {
            throw new PharmacyException("Faracêutico já cadastrado!");
        }
    }

    public Pharmaceutical updatePharmaceutical(Pharmaceutical pharmaceutical) {
        validateOnUpdatePharmaceutical(pharmaceutical);
        return repository.save(pharmaceutical);
    }

    public void validateOnUpdatePharmaceutical(Pharmaceutical pharmaceutical) {
        if(pharmaceutical == null) {
            throw new PharmacyException("Nenhum médico informado!");
        }
        if(!existsPharmaceutical(pharmaceutical.getId())) {
            throw new PharmacyException("Não existe médico cadastrado!");
        }
    }

    @Transactional
    public void addRegisterToPharmaceutical(UUID idPharmaceutical, RegulatoryPharmaceuticalBody regulatoryPharmaceuticalBody) {
        Pharmaceutical pharmaceutical = findPharmaceuticalById(idPharmaceutical);
        validateRegisterToPharmaceutical(pharmaceutical, regulatoryPharmaceuticalBody);
        regulatoryPharmaceuticalBody.setRegistrationDate(new Date());
        regulatoryPharmaceuticalBody.setPharmaceuticalName(pharmaceutical.getName());
        regulatoryPharmaceuticalBody.setPharmaceuticalLastName(pharmaceutical.getLastName());
        regulatoryPharmaceuticalBody.setPharmaceuticalCpf(pharmaceutical.getCpf());
        regulatoryPharmaceuticalBody.setEnabled(true);
        regulatoryPharmaceuticalBodyRepository.save(regulatoryPharmaceuticalBody);
        pharmaceutical.setRegulatoryBody(regulatoryPharmaceuticalBody);
        repository.save(pharmaceutical);
    }

    public void validateRegisterToPharmaceutical(Pharmaceutical pharmaceutical, RegulatoryPharmaceuticalBody regulatoryPharmaceuticalBody) {
        if(regulatoryPharmaceuticalBody == null) {
            throw new PharmacyException("Nenhum registro informado!");
        }if(pharmaceutical == null) {
            throw new PharmacyException("Nenhum famacêutico informado!");
        }
        if(!existsPharmaceutical(pharmaceutical.getId())) {
            throw new PharmacyException("Não existe famacêutico com o código " + pharmaceutical.getId() + " cadsatrado!");
        }
    }

    public void removeRegisterToPharmaceutical(UUID idPharmaceutical) {
        Pharmaceutical pharmaceutical = findPharmaceuticalById(idPharmaceutical);
        RegulatoryPharmaceuticalBody register = pharmaceutical.getRegulatoryBody();
        if(register == null) {
            throw new PharmacyException("Farmacêutico não possui nenhum registro");
        }
        register.setEnabled(false);
        regulatoryPharmaceuticalBodyRepository.save(register);
    }
}
