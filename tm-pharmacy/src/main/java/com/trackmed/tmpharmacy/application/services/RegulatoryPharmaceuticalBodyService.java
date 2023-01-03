package com.trackmed.tmpharmacy.application.services;

import com.trackmed.tmpharmacy.domains.entities.RegulatoryPharmaceuticalBody;
import com.trackmed.tmpharmacy.exceptions.PharmacyException;
import com.trackmed.tmpharmacy.infra.repositories.RegulatoryPharmaceuticalBodyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegulatoryPharmaceuticalBodyService {

    private final RegulatoryPharmaceuticalBodyRepository repository;

    public RegulatoryPharmaceuticalBody findRegulatoryByPharmaceuticalCpf(String cpf) {
        Optional<RegulatoryPharmaceuticalBody> register = repository.findByPharmaceuticalCpf(cpf);
        if(register.isEmpty()) {
            throw new PharmacyException("Não existe registro cadastrado para o cpf " + cpf);
        }
        return register.get();
    }

    public RegulatoryPharmaceuticalBody findRegulatoryById(UUID id) {
        Optional<RegulatoryPharmaceuticalBody> register = repository.findById(id);
        if(register.isEmpty()) {
            throw new PharmacyException("Não existe registro cadastrado com o código " + id);
        }
        return register.get();
    }

    public RegulatoryPharmaceuticalBody updateRegulatoryPharmaceuticalBody(RegulatoryPharmaceuticalBody register) {
        RegulatoryPharmaceuticalBody temp = findRegulatoryById(register.getId());

        if(!temp.getId().equals(register.getId())) {
            throw new PharmacyException("Registro não pode ser alterado, verifique se o código do  registro!");
        }
        return repository.save(register);
    }

    public void deleteRegulatoryPharmaceuticalBody(String idOrPharmaceuticalCpf) {
        Optional<RegulatoryPharmaceuticalBody> register = repository.findByPharmaceuticalCpf(idOrPharmaceuticalCpf);
        if(register.isEmpty()) {
            register = repository.findById(UUID.fromString(idOrPharmaceuticalCpf));
        }
        if(register.isEmpty()) {
            throw new PharmacyException("Nenhum registro encontrado para " + idOrPharmaceuticalCpf);
        }
        RegulatoryPharmaceuticalBody temp = register.get();
        temp.setEnabled(false);
        repository.save(temp);
    }

}
