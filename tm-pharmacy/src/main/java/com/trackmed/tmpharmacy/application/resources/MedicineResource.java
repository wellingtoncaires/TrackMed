package com.trackmed.tmpharmacy.application.resources;

import com.trackmed.tmpharmacy.domains.entities.enums.MedicineType;
import com.trackmed.tmpharmacy.domains.models.MedicineModel;
import com.trackmed.tmpharmacy.infra.clients.MockResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Service
@RestController
@RequestMapping("v1/farmacia/medicamentos")
@RequiredArgsConstructor
public class MedicineResource {

    private final MockResourceClient mockResourceClient;

    @GetMapping
    public ResponseEntity<List<MedicineModel>> getAllMedicines() {
        return mockResourceClient.getAllMedicines();
    }

    @GetMapping(params = "principio-ativo")
    public ResponseEntity<List<MedicineModel>> getByActivePrinciple(@RequestParam("principio-ativo") String activePrinciple) {
        return mockResourceClient.getByActivePrinciple(activePrinciple);
    }

    @GetMapping(params = "tipo-de-medicamento")
    public ResponseEntity<List<MedicineModel>> getByMedicineType(@RequestParam("tipo-de-medicamento") String medicineType) {
        return mockResourceClient.getByMedicineType(medicineType);
    }

    @GetMapping(params = "fabricante")
    public ResponseEntity<List<MedicineModel>> getByCompanyName(@RequestParam("fabricante") String company) {
        return mockResourceClient.getByCompanyName(company);
    }

    @GetMapping(params = "id")
    public ResponseEntity<MedicineModel> getMedicineById(@RequestParam("id") UUID id) {
        return mockResourceClient.getMedicineById(id);
    }

    @GetMapping(params = "nome-comercial")
    public ResponseEntity<MedicineModel> getByCommercialName(@RequestParam("id") String commercialName) {
        return mockResourceClient.getByCommercialName(commercialName);
    }
}
