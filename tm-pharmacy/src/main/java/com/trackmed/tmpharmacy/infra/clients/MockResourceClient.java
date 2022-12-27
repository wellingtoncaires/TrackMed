package com.trackmed.tmpharmacy.infra.clients;

import com.trackmed.tmpharmacy.domains.models.Pharmacy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.UUID;

@FeignClient(value = "tm-mock", path = "/v1/mock")
public interface MockResourceClient {

    @GetMapping(path = "/farmacia")
    ResponseEntity<List<Pharmacy>> findAllPharmacies();

    @GetMapping(path = "/farmacia", params = "id")
    ResponseEntity<Pharmacy> findPharmacyById(@RequestParam("id") UUID id);
}
