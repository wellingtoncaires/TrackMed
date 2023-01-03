package com.trackmed.tmpharmacy.application.resources;

import com.trackmed.tmpharmacy.application.services.PharmacyService;
import com.trackmed.tmpharmacy.domains.entities.Pharmacy;
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
@RequestMapping("v1/farmacia/farmacias")
@RequiredArgsConstructor
public class PharmacyResource {

    private final PharmacyService service;

    @GetMapping
    public ResponseEntity<List<Pharmacy>> findAllPharmacies() {
        List<Pharmacy> pharmacies = service.getAllPharmacies();
        return ResponseEntity.ok(pharmacies);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Pharmacy> findPharmacyById(@RequestParam("id") UUID id) {
        Pharmacy pharmacy = service.findPharmacyById(id);
        return ResponseEntity.ok(pharmacy);
    }

    @PostMapping
    public ResponseEntity<Pharmacy> savePharmacy(@RequestBody Pharmacy pharmacy) {
        service.savePharmacy(pharmacy);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(pharmacy.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PutMapping
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestBody Pharmacy pharmacy) {
        service.savePharmacy(pharmacy);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(pharmacy.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity removePharmacy(@RequestParam("id") UUID id) {
        service.deletePharmacy(id);
        return ResponseEntity.ok().build();
    }

}
