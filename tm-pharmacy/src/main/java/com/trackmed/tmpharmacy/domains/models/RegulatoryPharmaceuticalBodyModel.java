package com.trackmed.tmpharmacy.domains.models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class RegulatoryPharmaceuticalBodyModel {

    private UUID id;
    private String pharmaceuticalName;
    private String pharmaceuticalLastName;
    private Date registrationDate;
    private Date expirationDate;
    private boolean enabled;
}
