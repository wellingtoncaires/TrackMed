package com.trackmed.tmcitizen.application.resources;

import com.trackmed.tmcitizen.application.services.CitizenService;
import com.trackmed.tmcitizen.domains.entities.Citizen;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("v1/cidadao")
@RequiredArgsConstructor
public class CitizenResource {

    private final CitizenService service;

    @GetMapping
    public ResponseEntity<List<Citizen>> findAllCitizens() {
        List<Citizen> citizens = service.findAllCitizens();
        return ResponseEntity.ok(citizens);
    }

    @GetMapping(params = "username")
    public ResponseEntity<Citizen> findCitizenByUsername(@RequestParam("username") String username) {
        Citizen citizen = service.findCitizenByUsername(username);
        return ResponseEntity.ok(citizen);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Citizen> findCitizenByCpf(@RequestParam("cpf") String cpf) {
        Citizen citizen = service.findCitizenByCpf(cpf);
        return ResponseEntity.ok(citizen);
    }

    @GetMapping(params = "codigo")
    public ResponseEntity<Citizen> findCitizenByCpf(@RequestParam("codigo") UUID id) {
        Citizen citizen = service.findCitizenById(id);
        return ResponseEntity.ok(citizen);
    }

    @PostMapping
    public ResponseEntity<Citizen> saveCitizen(@RequestBody Citizen citizen) {
        service.saveCitizen(citizen);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(citizen.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

}
