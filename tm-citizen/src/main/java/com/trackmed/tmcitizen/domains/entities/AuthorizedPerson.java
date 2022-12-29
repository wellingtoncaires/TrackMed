package com.trackmed.tmcitizen.domains.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

//@Entity
//@Table(name = "CIT_T_AUTHORIZED_PERSON")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedPerson implements Serializable {
    public static final long serialVersionUID=1L;

    @EmbeddedId
    private AuthorizedPersonPK id;

    @ManyToOne
    @JoinColumn(name = "authorized_person_id", insertable = false, updatable = false, nullable = false)
    private Citizen authorizedPerson;

    @ManyToOne
    @JoinColumn(name = "citizen_treatment_id", insertable = false, updatable = false, nullable = false)
    private Citizen citizenTreatment;

    @ManyToOne
    @JoinColumn(name = "treatment_id", insertable = false, updatable = false, nullable = false)
    private Treatment treatment;

}
