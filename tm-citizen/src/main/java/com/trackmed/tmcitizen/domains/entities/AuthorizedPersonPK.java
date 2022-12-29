package com.trackmed.tmcitizen.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedPersonPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "authorized_person_id", nullable = false)
    private UUID authorizedPerson;

    @Column(name = "citizen_treatment_id", nullable = false)
    private UUID citizenTreatment;
}
