package com.trackmed.tmbase.application.services;

import com.trackmed.tmbase.domain.entities.Email;
import com.trackmed.tmbase.infra.repositories.EmailRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRespository repository;

    public void sendEmail(Email email) {

    }
}
