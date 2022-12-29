package com.trackmed.tmhospital.application.resources;

import com.trackmed.tmhospital.application.services.MedicService;
import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.enums.Speciality;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        return service.findAllMedicsClients();
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
    public ResponseEntity findMedicByCpf(@RequestParam("cpf") String cpf) {
        return service.findMedicClientByCpf(cpf);
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

    @GetMapping(params = "especialidade-medica")
    public ResponseEntity<List<Medic>> findMedicsBySpeciality(@RequestParam("especialidade-medica") String specialityString) {
        List<Medic> medics = service.findMedicsBySpeciality(specialityString);
        return ResponseEntity.ok(medics);
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

//    @PutMapping(params = "codigo-medico")
//    public ResponseEntity<Medic> updateMedic(@RequestParam("id") UUID id, @RequestBody Medic medic) {
//        service.updateMedic(medic, id);
//        URI headerLocation = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .query("id={id}")
//                .buildAndExpand(medic.getId())
//                .toUri();
//        return ResponseEntity.created(headerLocation).build();
//    }

    @DeleteMapping(params = "id")
    public void deleteMedic(@RequestParam("id") UUID id) {
        service.deleteMedic(id);
    }

    @GetMapping(path = "especialidades")
    public ResponseEntity<List<Speciality>> getAllSpecialities() {
        return service.getAllSpecialitiesClient();
    }
}
