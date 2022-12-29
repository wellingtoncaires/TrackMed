package com.trackmed.tmpharmacy.application.services;

import com.trackmed.tmpharmacy.domains.models.MedicineModel;
import com.trackmed.tmpharmacy.exceptions.CommunicationMicroServiceException;
import com.trackmed.tmpharmacy.exceptions.PharmacyException;
import com.trackmed.tmpharmacy.infra.clients.MockResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MockResourceClient mockResourceClient;

    public ResponseEntity<List<MedicineModel>> findAllMedicine() {
        try {
            return mockResourceClient.getAllMedicines();
        }catch (FeignException.FeignClientException e) {
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }

    public ResponseEntity<List<MedicineModel>> findByActivePrinciple(String activePrinciple) {
        try {
            return mockResourceClient.getByActivePrinciple(activePrinciple);
        }catch (FeignException.FeignClientException e) {
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }

    public ResponseEntity<List<MedicineModel>> findByMedicineType(String medicineType) {
        try {
            return mockResourceClient.getByMedicineType(medicineType);
        }catch (FeignException.FeignClientException e) {
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }

    public ResponseEntity<List<MedicineModel>> findByCompany(String company) {
        try {
            return mockResourceClient.getByCompanyName(company);
        }catch (FeignException.FeignClientException e) {
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }

    public ResponseEntity<MedicineModel> findByCommercialName(String commercialName) {
        try {
            return mockResourceClient.getByCommercialName(commercialName);
        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new PharmacyException("Não existe medicamento com esse nome!");
            }
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }

    public ResponseEntity<MedicineModel> findMedicineById(UUID id) {
        try {
            return mockResourceClient.getMedicineById(id);
        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new PharmacyException("Não existe medicamento com o código " + id + " cadastrado!");
            }
            throw new CommunicationMicroServiceException(e.getMessage(), e.status());
        }
    }
}
