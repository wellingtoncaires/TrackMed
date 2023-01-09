package com.trackmed.tmbase.application.service;

import com.trackmed.tmbase.infra.repositories.EmailRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRespository repository;
}
