package com.trackmed.tmhospital.domains.models;

import lombok.Data;

import java.util.UUID;

@Data
public class HospitalModel {

    private UUID id;
    private String name;
    private String webSite;
    private boolean operationalLicense;
}
