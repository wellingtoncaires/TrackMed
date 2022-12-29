package com.trackmed.tmpharmacy.application.resources;

import com.trackmed.tmpharmacy.domains.models.Pharmaceutical;
import com.trackmed.tmpharmacy.infra.clients.MockResourceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/farmacia/farmaceuticos")
@RequiredArgsConstructor
@Slf4j
public class PharmaceuticalResource {

    private final MockResourceClient mockResourceClient;

    @GetMapping
    public ResponseEntity<List<Pharmaceutical>> findAllPharmaceuticals() {
        return mockResourceClient.findAll();
    }

    @GetMapping(params = "id")
    public ResponseEntity<Pharmaceutical> findPharmaceuticalById(@RequestParam("id") UUID id) {
        return mockResourceClient.findPharmaceuticalById(id);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Pharmaceutical> findPharmaceuticalByCpf(@RequestParam("cpf") String cpf) {
        return mockResourceClient.findPharmaceuticalByCpf(cpf);
    }

}
