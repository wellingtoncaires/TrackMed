package com.trackmed.tmhospital.domains.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MedicModel {

    private UUID id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String cpf;
    private RegulatoryMedicBody regulatoryBody;

}
