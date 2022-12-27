package com.trackmed.tmhospital.application.resources;

import com.trackmed.tmhospital.domains.model.Hospital;
import com.trackmed.tmhospital.infra.clients.MockResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/hospital/hospitais")
@RequiredArgsConstructor
public class HospitalResource {

    private final MockResourceClient hospitalResourceClient;

    @GetMapping
    public ResponseEntity<List<Hospital>> findAllHospital() {
        return hospitalResourceClient.findAll();
    }

    @GetMapping(params = "id")
    public ResponseEntity<Hospital> findHospitalById(@RequestParam UUID id) {
        return hospitalResourceClient.findHospital(id);
    }
}
