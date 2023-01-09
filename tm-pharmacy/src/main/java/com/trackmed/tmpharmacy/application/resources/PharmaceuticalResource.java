package com.trackmed.tmpharmacy.application.resources;

import com.trackmed.tmpharmacy.application.services.PharmaceuticalService;
import com.trackmed.tmpharmacy.domains.entities.Pharmaceutical;
import com.trackmed.tmpharmacy.domains.entities.RegulatoryPharmaceuticalBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/farmacia/farmaceuticos")
@RequiredArgsConstructor
public class PharmaceuticalResource {

    private final PharmaceuticalService service;

    @GetMapping()
    public ResponseEntity<List<Pharmaceutical>> findAll() {
        List<Pharmaceutical> pharmaceuticals = service.findAllPharmaceuticals();
        return ResponseEntity.ok(pharmaceuticals);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Pharmaceutical> findPharmaceuticalByCpf(@RequestParam("cpf") String cpf) {
        Pharmaceutical pharmaceutical = service.findPharmaceuticalByCpf(cpf);
        return ResponseEntity.ok(pharmaceutical);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Pharmaceutical> findPharmaceuticalById(@RequestParam("id") UUID id) {
        Pharmaceutical pharmaceutical = service.findPharmaceuticalById(id);
        return ResponseEntity.ok(pharmaceutical);
    }

    @PostMapping
    public ResponseEntity<Pharmaceutical> savePharmaceutical(@RequestBody Pharmaceutical pharmaceutical) {
        service.savePharmaceutical(pharmaceutical);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(pharmaceutical.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PutMapping
    public ResponseEntity<Pharmaceutical> updatePharmaceutical(@RequestBody Pharmaceutical pharmaceutical) {
        service.updatePharmaceutical(pharmaceutical);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(pharmaceutical.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PostMapping(path = "adiciona-registro")
    public ResponseEntity saveRegister(@RequestParam("id") UUID id, @RequestBody RegulatoryPharmaceuticalBody register) {
        service.addRegisterToPharmaceutical(id, register);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity removeRegisterToPharmaceutical(@RequestParam("id") UUID idPharmaceutical) {
        service.removeRegisterToPharmaceutical(idPharmaceutical);
        return ResponseEntity.ok().build();
    }
}
