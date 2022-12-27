package com.trackmed.tmhospital.domains.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Hospital {

    private UUID id;
    private String name;
    private String webSite;
    private boolean operationalLicense;
}
