package com.trackmed.tmcitizen.application.services;

import com.trackmed.tmcitizen.domains.entities.Citizen;
import com.trackmed.tmcitizen.exceptions.CitizenException;
import com.trackmed.tmcitizen.infra.repositories.CitizenRepository;
import com.trackmed.tmcitizen.security.SecurityBeans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository repository;

    public List<Citizen> findAllCitizens() {
        return repository.findAll();
    }

    public Citizen findCitizenByUsername(String username) {
        Optional<Citizen> citizen = repository.findByUsername(username);
        if(citizen.isEmpty()) {
            throw new CitizenException("Não existe usuário com o username " + username);
        }
        return citizen.get();
    }

    public Citizen findCitizenByCpf(String cpf) {
        Optional<Citizen> citizen = repository.findByCpf(cpf);
        if(citizen.isEmpty()) {
            throw new CitizenException("Não existe usuário com o cpf " + cpf);
        }
        return citizen.get();
    }

    public Citizen findCitizenById(UUID id) {
        Optional<Citizen> citizen = repository.findById(id);
        if(citizen.isEmpty()) {
            throw new CitizenException("Não existe usuário com o código " + id);
        }
        return citizen.get();
    }

    public Citizen saveCitizen(Citizen citizen) {
        validateOnSaveCitizen(citizen);
        citizen.setPassword(new SecurityBeans().passwordEncoder().encode(citizen.getPassword()));
        return repository.save(citizen);
    }

    @Transactional
    private void validateOnSaveCitizen(Citizen citizen) {
        if(citizen == null) {
            throw new CitizenException("Nenhum registro informado!");
        }
        if(citizen.getBirthDate() == null) {
            throw new CitizenException("Necessário informar data de nascimento!");
        }
        LocalDate birthDate = LocalDate.from(citizen.getBirthDate().toInstant());
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        int age = period.getYears();
        if(age < 18 && citizen.getResponsiblePerson() == null) {
            throw new CitizenException("Usuários menor de idade precisam de um responsável!");
        }
        if(citizen.getPassword() == null) {
            //TODO Criar regra para validar senha
            throw new CitizenException("Sena inválida!");
        }
    }
}
