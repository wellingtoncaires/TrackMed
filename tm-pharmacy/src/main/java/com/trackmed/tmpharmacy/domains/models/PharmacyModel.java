package com.trackmed.tmpharmacy.domains.models;

import lombok.Data;
import java.util.UUID;

@Data
public class PharmacyModel {

    private UUID id;
    private String name;
    private boolean operationalLicense;
}
