package com.trackmed.tmmock.application.services;

import com.trackmed.tmmock.domains.entities.Pharmacy;
import com.trackmed.tmmock.exceptions.MockException;
import com.trackmed.tmmock.infra.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PharmacyService {

    private final PharmacyRepository repository;

    public List<Pharmacy> getAllPharmacies() {
        return repository.findAll();
    }

    public Pharmacy findPharmacyById(UUID id) {
        Optional<Pharmacy> pharmacy = repository.findById(id);
        if(pharmacy.isEmpty()) {
            throw new MockException("Nenhuma farmácia com o código " + id + " cadastrada!");
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
