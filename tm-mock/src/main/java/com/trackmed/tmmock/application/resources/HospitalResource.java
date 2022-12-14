package com.trackmed.tmmock.application.resources;

import com.trackmed.tmmock.application.services.HospitalService;
import com.trackmed.tmmock.domains.entities.Hospital;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("v1/mock/hospitais")
@RequiredArgsConstructor
public class HospitalResource {

    private final HospitalService service;

    /** TODO: Método para testes na fase de desenvolvimento, remover */
    @GetMapping()
    public ResponseEntity<List<Hospital>> findAllHospitals() {
        List<Hospital> hospitals = service.findAllHospital();
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Hospital> findHospital(@RequestParam("id") UUID id) {
        Hospital hospital = service.findHospitalById(id);
        return ResponseEntity.ok(hospital);
    }

    @PostMapping
    public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital) {
        service.saveHospital(hospital);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(hospital.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PutMapping()
    public ResponseEntity<Hospital> updateHospital(@RequestBody Hospital hospital) {
        service.updateHospital(hospital);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(hospital.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @DeleteMapping(params = "id")
    public void deleteHospital(@RequestParam("id") UUID id) {
        service.deleteHospital(id);
    }
}
