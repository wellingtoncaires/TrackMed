package com.trackmed.tmhospital.domains.models;

import com.trackmed.tmhospital.domains.enums.MedicineType;
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
