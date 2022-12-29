package com.trackmed.tmhospital.application.services;

import com.trackmed.tmhospital.domains.model.Hospital;
import com.trackmed.tmhospital.exceptions.CommunicationMicroServiceException;
import com.trackmed.tmhospital.exceptions.HospitalException;
import com.trackmed.tmhospital.infra.clients.MockResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final MockResourceClient mockResourceClient;

    public ResponseEntity<List<Hospital>> findAllHospital() {
        try {
            return mockResourceClient.findAll();
        }catch (FeignException.FeignClientException e) {
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }

    public ResponseEntity<Hospital> findHospitalById(@RequestParam UUID id) {
        try {
            return mockResourceClient.findHospital(id);
        }catch (FeignException.FeignClientException e) {
            int httpStatus = e.status();
            if(e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new HospitalException("Hospital não encontrado!");
            }
            throw new CommunicationMicroServiceException(e.getMessage(), httpStatus);
        }
    }
}
