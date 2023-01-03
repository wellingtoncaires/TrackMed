package com.trackmed.tmpharmacy.application.services;

import com.trackmed.tmpharmacy.domains.entities.Pharmacy;
import com.trackmed.tmpharmacy.exceptions.PharmacyException;
import com.trackmed.tmpharmacy.infra.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final PharmacyRepository repository;

    public List<Pharmacy> getAllPharmacies() {
        return repository.findAll();
    }

    public Pharmacy findPharmacyById(UUID id) {
        Optional<Pharmacy> pharmacy = repository.findById(id);
        if(pharmacy.isEmpty()) {
            throw new PharmacyException("Nenhuma farmácia com o código " + id + " cadastrada!");
        }
        return pharmacy.get();
    }

    public boolean existsPharmacy(UUID id) {
        return repository.findById(id).isPresent();
    }

    public Pharmacy savePharmacy(Pharmacy pharmacy) {
        return repository.save(pharmacy);
    }

    public void deletePharmacy(UUID id) {
        Pharmacy pharmacy = findPharmacyById(id);
        pharmacy.setOperationalLicense(false);
        savePharmacy(pharmacy);
    }
}
