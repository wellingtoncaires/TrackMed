package com.trackmed.tmpharmacy.domains.models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Pharmaceutical {

    private UUID id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String cpf;
    private UUID regulatoryPharmaceuticalBody;

}
