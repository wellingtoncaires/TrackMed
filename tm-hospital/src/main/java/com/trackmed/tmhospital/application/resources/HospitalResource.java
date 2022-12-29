package com.trackmed.tmhospital.application.resources;

import com.trackmed.tmhospital.application.services.HospitalService;
import com.trackmed.tmhospital.domains.models.HospitalModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/hospital/hospitais")
@RequiredArgsConstructor
public class HospitalResource {

    private final HospitalService service;

    @GetMapping
    public ResponseEntity<List<HospitalModel>> findAllHospital()  {
        return service.findAllHospital();
    }

    @GetMapping(params = "id")
    public ResponseEntity findHospitalById(@RequestParam UUID id) {
        return service.findHospitalById(id);
    }
}
