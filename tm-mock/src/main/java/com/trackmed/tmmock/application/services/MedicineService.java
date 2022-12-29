package com.trackmed.tmmock.application.services;

import com.trackmed.tmmock.domains.entities.Medicine;
import com.trackmed.tmmock.domains.enums.MedicineType;
import com.trackmed.tmmock.exceptions.MockException;
import com.trackmed.tmmock.infra.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository repository;

    public List<Medicine> findAllMedicine() {
        return repository.findAll();
    }

    public List<Medicine> findByActivePrinciple(String activePrinciple) {
        return repository.findByActivePrinciple(activePrinciple);
    }

    public List<Medicine> findByMedicineType(MedicineType medicineType) {
        return repository.findByMedicineType(medicineType);
    }

    public List<Medicine> findByCompany(String company) {
        return repository.findByCompany(company);
    }

    public Medicine findByCommercialName(String commercialName) {
        Optional<Medicine> medicine = repository.findByCommercialName(commercialName);
        if(medicine.isEmpty()) {
            throw new MockException("Não existe medicamento com esse nome!");
        }
        return medicine.get();
    }

    public Medicine findMedicineById(UUID id) {
        Optional<Medicine> medicine = repository.findById(id);
        if(medicine.isEmpty()) {
            throw new MockException("Não existe medicamento com o código " + id + " cadastrado!");
        }
        if(!medicine.get().isApproved()) {
            throw new MockException("O medicamento  " + medicine.get().getCommercialName() + " no momento não está lilberado para o uso!");
        }
        return medicine.get();
    }

    public boolean existsMedicine(UUID id) {
        return repository.findById(id).isPresent();
    }

    public Medicine saveMedicine(Medicine medicine) {
        validateOnSaveAndUpdateMedicine(medicine);
        return repository.save(medicine);
    }

    private void validateOnSaveAndUpdateMedicine(Medicine medicine) {
        if(medicine == null) {
            throw new MockException("Não existe medicamento informado!");
        }
        if(medicine.getActivePrinciple().isEmpty() || medicine.getActivePrinciple().isBlank()) {
            throw new MockException("Necessário informar o princípio ativo do medicamento!");
        }
        if(medicine.getMedicineType() == null) {
            throw new MockException("Necessário informar o tipo do medicamento!");
        }
        if(medicine.getCommercialName().isEmpty() || medicine.getCommercialName().isBlank()) {
            throw new MockException("Necessário informar o nome comercial do medicamento!");
        }
        if(medicine.getPosology() == null) {
            throw new MockException("Necessário informar a posologia do medicamento!");
        }
        if(medicine.getCompany().isEmpty()) {
            throw new MockException("Necessário informar o fabricante do medicamento!");
        }
    }

    public Medicine updateMEdicine(Medicine medicine) {
        if(!existsMedicine(medicine.getId())) {
            throw new MockException("Não existe medicamento informado!");
        }
        validateOnSaveAndUpdateMedicine(medicine);
        return repository.save(medicine);
    }

    public void deleteMedicine(UUID id) {
        Optional<Medicine> medicine = repository.findById(id);
        if(medicine.isEmpty()) {
            throw new MockException("Não existe medicamento com o código " + id + "!");
        }
        medicine.get().setApproved(false);
        repository.save(medicine.get());
    }

}
