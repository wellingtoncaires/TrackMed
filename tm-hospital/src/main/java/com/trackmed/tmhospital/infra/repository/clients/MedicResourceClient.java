package com.trackmed.tmhospital.infra.repository.clients;

import com.trackmed.tmhospital.domains.enums.Speciality;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "tm-mock", path = "v1/mock/medico")
public interface MedicResourceClient {

    @GetMapping(path = "especialidades")
    ResponseEntity<List<Speciality>> getAllSpecialities();
}
