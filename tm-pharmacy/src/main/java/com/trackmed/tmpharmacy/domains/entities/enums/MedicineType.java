package com.trackmed.tmpharmacy.domains.entities.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MedicineType {
    ADESIVO("ADESIVO"),
    CAPSULA("CAPSULA"),
    CREME("CREME"),
    COMPRIMIDO("COMPRIMIDO"),
    GOTAS("GOTAS"),
    INJETAVEL("INJETÁVEL"),
    POMADA("POMADA"),
    SUPOSITORIO("SUPOSITÓRIO"),
    XAROPE("XAROPE");

    private String description;

    MedicineType(String description) {
        this.description = description;
    }
}
