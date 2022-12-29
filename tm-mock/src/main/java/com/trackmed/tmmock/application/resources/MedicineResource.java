package com.trackmed.tmmock.application.resources;

import com.trackmed.tmmock.application.services.MedicineService;
import com.trackmed.tmmock.domains.entities.Medicine;
import com.trackmed.tmmock.domains.enums.MedicineType;
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
@RequestMapping("v1/mock/medicamentos")
@RequiredArgsConstructor
public class MedicineResource {

    private final MedicineService service;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicineList = service.findAllMedicine();
        return ResponseEntity.ok(medicineList);
    }

    @GetMapping(params = "principio-ativo")
    public ResponseEntity<List<Medicine>> getByActivePrinciple(@RequestParam("principio-ativo") String activePrinciple) {
        List<Medicine> medicineList = service.findByActivePrinciple(activePrinciple);
        return ResponseEntity.ok(medicineList);
    }

    @GetMapping(params = "tipo-de-medicamento")
    public ResponseEntity<List<Medicine>> getByMedicineType(@RequestParam("tipo-de-medicamento") String medicineType) {
        MedicineType type = MedicineType.valueOf(medicineType.toUpperCase());
        List<Medicine> medicineList = service.findByMedicineType(type);
        return ResponseEntity.ok(medicineList);
    }

    @GetMapping(params = "fabricante")
    public ResponseEntity<List<Medicine>> getByCompanyName(@RequestParam("fabricante") String company) {
        List<Medicine> medicineList = service.findByCompany(company);
        return ResponseEntity.ok(medicineList);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Medicine> getMedicineById(@RequestParam("id") UUID id) {
        Medicine medicine = service.findMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @GetMapping(params = "nome-comercial")
    public ResponseEntity<Medicine> getByCommercialName(@RequestParam("id") String commercialName) {
        Medicine medicine = service.findByCommercialName(commercialName);
        return ResponseEntity.ok(medicine);
    }

    @PostMapping
    public ResponseEntity<Medicine> saveMedicine(@RequestBody Medicine medicine) {
        service.saveMedicine(medicine);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(medicine.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @PutMapping
    public ResponseEntity<Medicine> updateMedic(@RequestBody Medicine medicine) {
        service.updateMEdicine(medicine);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("id={id}")
                .buildAndExpand(medicine.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity deleteMedicine(@RequestParam("id") UUID id) {
        service.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }

}
