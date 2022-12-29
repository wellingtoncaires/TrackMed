package com.trackmed.tmpharmacy.infra.clients;

import com.trackmed.tmpharmacy.domains.models.Pharmaceutical;
import com.trackmed.tmpharmacy.domains.models.Pharmacy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.UUID;

@FeignClient(value = "tm-mock", path = "/v1/mock")
public interface MockResourceClient {

    @GetMapping(path = "/farmacias")
    ResponseEntity<List<Pharmacy>> findAllPharmacies();

    @GetMapping(path = "/farmacias", params = "id")
    ResponseEntity<Pharmacy> findPharmacyById(@RequestParam("id") UUID id);

    @GetMapping(path = "/farmaceuticos")
    public ResponseEntity<List<Pharmaceutical>> findAll();

    @GetMapping(path = "/farmaceuticos", params = "cpf")
    public ResponseEntity<Pharmaceutical> findPharmaceuticalByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(path = "/farmaceuticos", params = "id")
    public ResponseEntity<Pharmaceutical> findPharmaceuticalById(@RequestParam("id") UUID id);
}
