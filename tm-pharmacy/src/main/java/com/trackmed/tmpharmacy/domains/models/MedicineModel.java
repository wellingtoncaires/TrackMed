package com.trackmed.tmpharmacy.domains.models;

import com.trackmed.tmpharmacy.domains.entities.enums.MedicineType;
import lombok.Data;

import java.util.UUID;

@Data
public class MedicineModel {

    private UUID id;
    private String activePrinciple;
    private MedicineType medicineType;
    private String commercialName;
    private Double posology;
    private String company;
    private boolean approved;
}
