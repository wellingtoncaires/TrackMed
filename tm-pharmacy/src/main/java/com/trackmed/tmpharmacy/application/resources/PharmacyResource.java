package com.trackmed.tmpharmacy.application.resources;

import com.trackmed.tmpharmacy.domains.models.PharmacyModel;
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
@RequestMapping("v1/farmacia/farmacias")
@RequiredArgsConstructor
@Slf4j
public class PharmacyResource {

    private final MockResourceClient mockResourceClient;

    @GetMapping
    public ResponseEntity<List<PharmacyModel>> findAllPharmacies() {
        return mockResourceClient.findAllPharmacies();
    }

    @GetMapping(params = "id")
    public ResponseEntity<PharmacyModel> findPharmacyById(@RequestParam("id") UUID id) {
        return mockResourceClient.findPharmacyById(id);
    }
}
