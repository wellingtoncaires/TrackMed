package com.trackmed.tmhospital.application.resources;

import com.trackmed.tmhospital.application.services.ReceptionistService;
import com.trackmed.tmhospital.domains.entities.Receptionist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("v1/hospital/recepcao")
@RequiredArgsConstructor
@Slf4j
public class ReceptionResource {

    private final ReceptionistService service;


    /** TODO: Método para testes na fase de desenvolvimento, remover */
    @GetMapping()
    public ResponseEntity<List<Receptionist>> findAll() {
        List<Receptionist> receptionists = service.findAll();
        return ResponseEntity.ok().body(receptionists);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Receptionist> findReceptionist(@RequestParam("id") UUID id) {
        Receptionist receptionist = service.findReceptionistById(id);
        return ResponseEntity.ok().body(receptionist);
    }

    @GetMapping(params = "username")
    public ResponseEntity<Receptionist> findReceptionistByUsername(@RequestParam("username") String username) {
        Receptionist receptionist = service.findReceptionistByUsername(username);
        return ResponseEntity.ok().body(receptionist);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Receptionist> findReceptionistByEmail(@RequestParam("email") String email) {
        Receptionist receptionist = service.findReceptionistByEmail(email);
        return ResponseEntity.ok().body(receptionist);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Receptionist> findReceptionistByCpf(@RequestParam("cpf") String cpf) {
        Receptionist receptionist = service.findReceptionistByCpf(cpf);
        return ResponseEntity.ok().body(receptionist);
    }

    @GetMapping(params = "id-hospital")
    public ResponseEntity<List<Receptionist>> findReceptionistByHospital(@RequestParam("id-hospital") UUID idHospital) {
        List<Receptionist> listReceptionist = service.findReceptionistByHospital(idHospital);
        return ResponseEntity.ok().body(listReceptionist);
    }

    @PostMapping
    public ResponseEntity<Receptionist> saveReceptionist(@RequestBody Receptionist receptionist,
                                                         @RequestParam("id-hospital") UUID idHospital) {
        service.saveReceptionist(receptionist, idHospital);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(receptionist.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PutMapping
    public ResponseEntity<Receptionist> updateReceptionist(@RequestBody Receptionist receptionist) {
        service.updateReceptionist(receptionist);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(receptionist.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }


}
