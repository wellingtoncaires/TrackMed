package com.trackmed.tmpharmacy.application.resources;

import com.trackmed.tmpharmacy.application.services.RegulatoryPharmaceuticalBodyService;
import com.trackmed.tmpharmacy.domains.entities.RegulatoryPharmaceuticalBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("v1/farmacia/registros-farmaceuticos")
@RequiredArgsConstructor
public class RegulatoryPharmaceuticalBodyResource {

    private final RegulatoryPharmaceuticalBodyService service;

    @GetMapping(params = "cpf")
    public ResponseEntity<RegulatoryPharmaceuticalBody> findRegulatoryPharmaceuticalByCpfPharmaceutical(@RequestParam("cpf") String cpf) {
        RegulatoryPharmaceuticalBody register = service.findRegulatoryByPharmaceuticalCpf(cpf);
        return ResponseEntity.ok(register);
    }

    @GetMapping(params = "id")
    public ResponseEntity<RegulatoryPharmaceuticalBody> findRegulatoryPharmaceuticalById(@RequestParam("id") UUID id) {
        RegulatoryPharmaceuticalBody register = service.findRegulatoryById(id);
        return ResponseEntity.ok(register);
    }

    @PutMapping
    public ResponseEntity<RegulatoryPharmaceuticalBody> updateRegulatoryPharmaceuticalBody(@RequestBody RegulatoryPharmaceuticalBody regulatoryPharmaceuticalBody) {
        RegulatoryPharmaceuticalBody register = service.updateRegulatoryPharmaceuticalBody(regulatoryPharmaceuticalBody);
        return ResponseEntity.ok(register);
    }

    @DeleteMapping()
    public ResponseEntity deleteRegulatoryPharmaceuticalBody(@RequestParam() String idOrPharmaceuticalCpf) {
        service.deleteRegulatoryPharmaceuticalBody(idOrPharmaceuticalCpf);
        return ResponseEntity.ok().build();
    }
}
