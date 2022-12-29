package com.trackmed.tmhospital.infra.clients;

import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.enums.Speciality;
import com.trackmed.tmhospital.domains.model.Hospital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "tm-mock", path = "/v1/mock")
public interface MockResourceClient {

    @GetMapping(path = "/hospitais")
    ResponseEntity<List<Hospital>> findAll();

    @GetMapping(path = "/hospitais", params = "id")
    ResponseEntity<Hospital> findHospital(@RequestParam("id") UUID id);

    @PutMapping(path = "/hospitais", params = "id")
    ResponseEntity<Hospital> updateHospital(@RequestParam("id") UUID id, @RequestBody Hospital hospital);

    @GetMapping(path = "/medicos/especialidades")
    ResponseEntity<List<Speciality>> getAllSpecialities();

    @GetMapping(path = "/medicos")
    public ResponseEntity<List<Medic>> findAllMedics();

    @GetMapping(path = "/medicos", params = "cpf")
    public ResponseEntity<Medic> findMedicByCpf(@RequestParam("cpf") String cpf);
}
