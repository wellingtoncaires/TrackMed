package com.trackmed.tmmock.application.resources;

import com.trackmed.tmmock.application.services.MedicService;
import com.trackmed.tmmock.domains.entities.Medic;
import com.trackmed.tmmock.domains.entities.RegulatoryMedicBody;
import com.trackmed.tmmock.domains.enums.Speciality;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

@Service
@RestController
@RequestMapping("v1/mock/medico")
@RequiredArgsConstructor
@Slf4j
public class MedicResource {

    private final MedicService service;

    /** TODO: Método para testes na fase de desenvolvimento, remover */
    @GetMapping()
    public ResponseEntity<List<Medic>> findAll() {
        List<Medic> medics = service.findAllMedics();
        return ResponseEntity.ok(medics);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Medic> findMedicByCpf(@RequestParam("cpf") String cpf) {
        Medic medic = service.findMedicByCpf(cpf);
        return ResponseEntity.ok(medic);
    }

    @GetMapping(params = "especialidade-medica")
    public ResponseEntity<List<Medic>> findMedicsBySpeciality(@RequestParam("especialidade-medica") String specialityString) {
        List<Medic> medics = service.findMedicsBySpeciality(specialityString);
        return ResponseEntity.ok(medics);
    }

    @GetMapping(path = "especialidades")
    public ResponseEntity<List<Speciality>> getAllSpecialities() {
        return ResponseEntity.ok(service.getAllSpecialities());
    }

    @PostMapping
    public ResponseEntity<Medic> saveMedic(@RequestBody Medic medic) {
        service.saveMedic(medic);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(medic.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PutMapping
    public ResponseEntity<Medic> updateMedic(@RequestBody Medic medic) {
        service.updateMedic(medic);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(medic.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PostMapping(path = "adiciona-registro")
    public ResponseEntity saveRegister(@RequestParam("id") UUID id, @RequestBody RegulatoryMedicBody register) {
        service.addRegisterToMedic(id, register);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity removeRegisterToMedic(@RequestParam("idMedic") UUID idMedic, @RequestParam("idRegulatory") UUID idRegulatory) {
        service.removeRegisterToMedic(idMedic, idRegulatory);
        return ResponseEntity.ok().build();
    }
}
