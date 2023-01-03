package com.trackmed.tmhospital.infra.clients;

import com.trackmed.tmhospital.domains.entities.Medic;
import com.trackmed.tmhospital.domains.enums.Speciality;
import com.trackmed.tmhospital.domains.models.MedicineModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "tm-mock", path = "/v1/mock")
public interface MockResourceClient {

    @GetMapping(path = "medicamentos")
    ResponseEntity<List<MedicineModel>> getAllMedicines();

    @GetMapping(path = "medicamentos", params = "principio-ativo")
    ResponseEntity<List<MedicineModel>> getByActivePrinciple(@RequestParam("principio-ativo") String activePrinciple);

    @GetMapping(path = "medicamentos", params = "tipo-de-medicamento")
    ResponseEntity<List<MedicineModel>> getByMedicineType(@RequestParam("tipo-de-medicamento") String medicineType);

    @GetMapping(path = "medicamentos", params = "fabricante")
    ResponseEntity<List<MedicineModel>> getByCompanyName(@RequestParam("fabricante") String company);

    @GetMapping(path = "medicamentos", params = "id")
    ResponseEntity<MedicineModel> getMedicineById(@RequestParam("id") UUID id);

    @GetMapping(path = "medicamentos", params = "nome-comercial")
    ResponseEntity<MedicineModel> getByCommercialName(@RequestParam("id") String commercialName);


}
