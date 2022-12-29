package com.trackmed.tmcitizen.application.services;

import com.trackmed.tmcitizen.domains.entities.Citizen;
import com.trackmed.tmcitizen.infra.repositories.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl {

    private final CitizenRepository repository;

    public Citizen findByUsername(String username) {
        Optional<Citizen> citizen = repository.findByUsername(username);
        return citizen.get();
    }
}
