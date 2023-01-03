package com.trackmed.tmhospital.application.resources;

import com.trackmed.tmhospital.application.services.MedicService;
import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.entities.RegulatoryMedicBody;
import com.trackmed.tmhospital.domains.enums.Speciality;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("v1/hospital/medicos")
@RequiredArgsConstructor
public class MedicResource {

    private final MedicService service;

    /** TODO: Método para testes na fase de desenvolvimento, remover */
    @GetMapping()
    public ResponseEntity<List<Medic>> findAll() {
        List<Medic> medics = service.findAllMedics();
        return ResponseEntity.ok(medics);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Medic> findMedic(@RequestParam("id") UUID id) {
        Medic medic = service.findMedicById(id);
        return ResponseEntity.ok(medic);
    }

    @GetMapping(params = "nome-de-usuario")
    public ResponseEntity<Medic> findMedicByUsername(@RequestParam("nome-de-usuario") String username) {
        Medic medic = service.findMedicByUsername(username);
        return ResponseEntity.ok(medic);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Medic> findMedicByCpf(@RequestParam("cpf") String cpf) {
        Medic medic = service.findMedicByCpf(cpf);
        return ResponseEntity.ok(medic);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Medic> findMedicByEmail(@RequestParam("email") String email) {
        Medic medic = service.findMedicByEmail(email);
        return ResponseEntity.ok(medic);
    }

    @GetMapping(params = "hospital-id")
    public ResponseEntity<List<Medic>> findMedicByHospitalId(@RequestParam("hospital-id") UUID hospitalId) {
        List<Medic> medics = service.findMedicByHospitalId(hospitalId);
        return ResponseEntity.ok(medics);
    }

//    @GetMapping(params = "especialidade-medica")
//    public ResponseEntity<List<Medic>> findMedicsBySpeciality(@RequestParam("especialidade-medica") String specialityString) {
//        List<Medic> medics = service.findMedicsBySpeciality(specialityString);
//        return ResponseEntity.ok(medics);
//    }

    @GetMapping(path = "especialidades")
    public ResponseEntity<List<Speciality>> getAllSpecialities() {
        List<Speciality> specialities = service.getAllSpecialities();
        return ResponseEntity.ok(specialities);
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

    @PostMapping(path = "adiciona-registro")
    public ResponseEntity saveRegister(@RequestParam("id") UUID id, @RequestBody RegulatoryMedicBody register) {
        service.addRegisterToMedic(id, register);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Medic> updateMedic(@RequestBody Medic medic) {
        return ResponseEntity.ok(service.updateMedic(medic));
    }

    @DeleteMapping(params = "id")
    public void deleteMedic(@RequestParam("id") UUID id) {
        service.deleteMedic(id);
    }
}
